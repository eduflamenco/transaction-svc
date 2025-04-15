package com.transaction.executors;

public interface IExecutor <O, I>{
    O execute(I input);
}
