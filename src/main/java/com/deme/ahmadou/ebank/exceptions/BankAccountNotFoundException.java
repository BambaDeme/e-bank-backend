package com.deme.ahmadou.ebank.exceptions;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException(String message){
        super(message);
    }
}
