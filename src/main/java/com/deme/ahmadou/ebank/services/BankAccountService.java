package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.entities.BankAccount;

public interface BankAccountService {
    BankAccount saveBankAccount(double initialBalance, String type, Long customerID);

    BankAccount getBankAccount(String accountId);
}
