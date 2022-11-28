package com.deme.ahmadou.ebank.exceptions;

public class BalanceNotSufficientException extends Exception{
    public BalanceNotSufficientException(String message){
        super(message);
    }
}
