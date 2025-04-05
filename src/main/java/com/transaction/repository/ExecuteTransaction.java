package com.transaction.repository;

import com.transaction.entity.Account;
import com.transaction.entity.Client;
import com.transaction.entity.Entry;
import com.transaction.pojo.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class ExecuteTransaction {

    private final ClientRepo clientRepo;
    private final AccountRepo accountRepo;
    private final EntryRepo entryRepo;
    private final TransactionRepo transactionRepo;

    public ExecuteTransaction(ClientRepo clientRepo, AccountRepo accountRepo, TransactionRepo transactionRepo, EntryRepo entryRepo) {
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
        this.entryRepo = entryRepo;
        this.transactionRepo = transactionRepo;
    }

    @Transactional
    public String execute(Transaction transaction) {
        try {
            Client client = clientRepo.findByUsername(transaction.user());
            if ( client!= null){
                Account fromAcc = accountRepo.findByAccountNumber(transaction.fromAccount());
                Account toAcc = accountRepo.findByAccountNumber(transaction.toAccount());
                if (fromAcc != null && toAcc != null){
                    com.transaction.entity.Transaction trx = transactionRepo.save(buildNewTransactionRecord(transaction, client, fromAcc, toAcc));
                    entryRepo.save(CreateEntry(transaction.amount(), fromAcc.getAccountNumber(), trx.getId(), TransactionType.RETIRO));
                    entryRepo.save(CreateEntry(transaction.amount(), toAcc.getAccountNumber(), trx.getId(), TransactionType.DEPOSITO));
                    accountRepo.save(UpdateAccountBalance(fromAcc, -transaction.amount()));
                    accountRepo.save(UpdateAccountBalance(toAcc, transaction.amount()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Something went wrong";
        }
        return "Transaction executed successfully";
    }

    private com.transaction.entity.Transaction buildNewTransactionRecord(Transaction transaction, Client client, Account fromAcc, Account toAcc){
        com.transaction.entity.Transaction tran = new com.transaction.entity.Transaction();
        tran.setFromAccount(fromAcc);
        tran.setToAccount(toAcc);
        tran.setAmount(transaction.amount());
        tran.setClient(client);
        return tran;
    }

    private Account UpdateAccountBalance(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    private Entry CreateEntry(Double amount, String account, UUID trxId, Enum type) {
        Double newAmount = type == TransactionType.RETIRO ? amount * -1 : amount;
        Entry entry = new Entry();
        entry.setAccountNumber(account);
        entry.setType(type.toString());
        entry.setAmount(newAmount);
        entry.setTransactionId(trxId);
        return entry;
    }
}
