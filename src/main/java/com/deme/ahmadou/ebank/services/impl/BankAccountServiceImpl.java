package com.deme.ahmadou.ebank.services.impl;

import com.deme.ahmadou.ebank.dtos.BankAccountDto;
import com.deme.ahmadou.ebank.dtos.CurrentBankAccountDto;
import com.deme.ahmadou.ebank.dtos.SavingBankAccountDto;
import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import com.deme.ahmadou.ebank.enums.AccountStatus;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;
import com.deme.ahmadou.ebank.mappers.BankAccountServiceMapperImpl;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import com.deme.ahmadou.ebank.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    private final BankAccountServiceMapperImpl bankAccountServiceMapper;

    @Override
    public CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double  overDraft, Long customerID) throws CustomerNotFoundException{

        // find related customer
        Customer customer = customerRepository.findById(customerID).orElseThrow(()-> new CustomerNotFoundException("Customer with id {} not found"));

        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setCustomer(customer);
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setOverDraft(overDraft);

        log.info("Saving new current account {}",currentAccount);

        bankAccountRepository.save(currentAccount);

        return bankAccountServiceMapper.fromCurrentAccount(currentAccount);
    }

    @Override
    public SavingBankAccountDto saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        // find related customer
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setCustomer(customer);
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setInterestRate(interestRate);

        log.info("Saving new saving account {}",savingAccount);

        bankAccountRepository.save(savingAccount);
        return bankAccountServiceMapper.fromSavingAccount(savingAccount);
    }

    @Override
    public BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException {

        BankAccount bankAccount =  bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        if(bankAccount instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;

            return bankAccountServiceMapper.fromCurrentAccount(currentAccount);
        }
        else{
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountServiceMapper.fromSavingAccount(savingAccount);
        }
    }

    @Override
    public List<BankAccountDto> getBankAccountList() {
        return bankAccountRepository.findAll().stream().map(bankAccount -> {
            return bankAccount instanceof CurrentAccount ?
                    bankAccountServiceMapper.fromCurrentAccount((CurrentAccount) bankAccount) :
                    bankAccountServiceMapper.fromSavingAccount((SavingAccount) bankAccount);
        }).collect(Collectors.toList());
    }
}
