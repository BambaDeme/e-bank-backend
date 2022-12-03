package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.dtos.*;
import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerID) throws CustomerNotFoundException;

    SavingBankAccountDto saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccountDto> getBankAccountList();

    List<AccountOperationDto> accountHistory(String accountId) throws BankAccountNotFoundException;

    AccountHistoryDto pageOperations(String accountId,int page,int size) throws BankAccountNotFoundException;
}
