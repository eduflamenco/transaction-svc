begin;
create table public.clients(
                               client_id serial primary key,
                               username varchar(10) not null,
                               name varchar(20) not null,
                               last_name varchar(20),
                               created_date timestamp not null default now(),
                               is_active bool not null default true
);

create table public.accounts(
                                id serial primary key,
                                account_number varchar(12) unique not null,
                                balance numeric(7,2) default 0.00,
                                client_id INTEGER not null,
                                created_date timestamp not null default now(),
                                is_active bool not null default true
);

alter table public.accounts add constraint fk_client
    foreign key (client_id) references public.clients(client_id);

create table public.transactions(
                                    id UUID primary key,
                                    from_account_number varchar(12) not null,
                                    to_account_number varchar(12) not null,
                                    amount numeric(7,2) default 0.00,
                                    client_id INTEGER not null,
                                    created_date timestamp not null default now()
);

alter table public.transactions add constraint fk_from_account
    foreign key(from_account_number) references public.accounts(account_number);

alter table public.transactions add constraint fk_to_account
    foreign key(to_account_number) references public.accounts(account_number);

create table public.entry(
                             entry_id serial primary key,
                             account_number varchar(12) not null,
                             type varchar(20) not null,
                             amount numeric(7,2) not null default 0.00,
                             transaction_id UUID not null,
                             created_date timestamp not null default now()
);
alter table public.entry add constraint fk_entry_account
    foreign key(account_number) references public.accounts(account_number);

alter table public.entry add constraint fk_transaction_id
    foreign key(transaction_id) references public.transactions(id);

commit;

insert into public.clients (username, name, last_name) values('edflamenco', 'Eduardo','Flamenco');
insert into public.clients (username, name, last_name) values('eflamenco', 'Ernesto','flamenco');

INSERT INTO public.accounts(account_number, balance, client_id)
VALUES('RA00001', 564.25, 1);
INSERT INTO public.accounts(account_number, balance, client_id)
VALUES('RA00002', 896.32, 2);

select * from clients c ;

select * from accounts a;

select * from bank.public.transactions t ;

select * from bank.public.entry;

