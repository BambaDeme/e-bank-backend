package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.exceptions.BalanceNotSufficientException;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface AccountOperationService {

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit (String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException ,BalanceNotSufficientException;
}
