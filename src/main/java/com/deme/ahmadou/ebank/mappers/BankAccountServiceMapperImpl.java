package com.deme.ahmadou.ebank.mappers;


import com.deme.ahmadou.ebank.dtos.CurrentBankAccountDto;
import com.deme.ahmadou.ebank.dtos.SavingBankAccountDto;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceMapperImpl {

    private final CustomerServiceMapperImpl customerServiceMapper;
    // transform current account to current account dto
    public CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();

        BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
        currentBankAccountDto.setCustomerDto(customerServiceMapper.fromCustomer(currentAccount.getCustomer()));

        return currentBankAccountDto;
    }

    // transform current account dto to current account
    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
        currentAccount.setCustomer(customerServiceMapper.fromCustomerDto(currentBankAccountDto.getCustomerDto()));

        return currentAccount;
    }

    // transform saving account to saving account dto
    public SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDto savingBankAccountDto = new SavingBankAccountDto();

        BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
        savingBankAccountDto.setCustomerDto(customerServiceMapper.fromCustomer(savingAccount.getCustomer()));


        return savingBankAccountDto;
    }

    // transform saving account dto to saving account
    public SavingAccount fromSavingAccountDto(SavingBankAccountDto savingBankAccountDto){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
        savingAccount.setCustomer(customerServiceMapper.fromCustomerDto(savingBankAccountDto.getCustomerDto()));

        return savingAccount;
    }
}
