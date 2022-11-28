package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;

public interface BankAccountService {
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerID) throws CustomerNotFoundException;

    SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
}
