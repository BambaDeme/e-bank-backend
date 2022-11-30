package com.deme.ahmadou.ebank.services.impl;

import com.deme.ahmadou.ebank.entities.AccountOperation;
import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.enums.OperationType;
import com.deme.ahmadou.ebank.exceptions.BalanceNotSufficientException;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.repositories.AccountOperationRepository;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.services.AccountOperationService;
import com.deme.ahmadou.ebank.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountOperationServiceImpl implements AccountOperationService {

    private final BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;
    private final AccountOperationRepository accountOperationRepository;

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount =  bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        if(bankAccount.getBalance()<amount){
            throw new BalanceNotSufficientException("Balance not sufficient ");
        }

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAccount(bankAccount);
        accountOperation.setAmount(amount);

        accountOperation.setDescription(description);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount =  bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setAccount(bankAccount);

        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException,BalanceNotSufficientException {
        debit(accountIdSource,amount,"transfer to"+accountIdDestination);
        credit(accountIdDestination,amount,"transfer from "+accountIdSource);
    }
}
