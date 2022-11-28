package com.deme.ahmadou.ebank.services.impl;

import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import com.deme.ahmadou.ebank.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double  overDraft, Long customerID) throws CustomerNotFoundException{

        // find related customer
        Customer customer = customerRepository.findById(customerID).orElseThrow(()-> new CustomerNotFoundException("Customer with id {} not found"));


        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);

        return bankAccountRepository.save(currentAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        // find related customer
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);

        return bankAccountRepository.save(savingAccount);
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException {
        return  bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    }
}
