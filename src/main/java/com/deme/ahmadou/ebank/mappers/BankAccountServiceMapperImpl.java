package com.deme.ahmadou.ebank.mappers;


import com.deme.ahmadou.ebank.dtos.CurrentBankAccountDto;
import com.deme.ahmadou.ebank.dtos.SavingBankAccountDto;
import com.deme.ahmadou.ebank.entities.CurrentAccount;
import com.deme.ahmadou.ebank.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceMapperImpl {

    // transform current account to current account dto
    public CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();

        BeanUtils.copyProperties(currentAccount,currentBankAccountDto);

        return currentBankAccountDto;
    }

    // transform current account dto to current account
    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDto,currentAccount);

        return currentAccount;
    }

    // transform saving account to saving account dto
    public SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDto savingBankAccountDto = new SavingBankAccountDto();

        BeanUtils.copyProperties(savingAccount,savingBankAccountDto);

        return savingBankAccountDto;
    }

    // transform saving account dto to saving account
    public SavingAccount fromSavingAccountDto(SavingBankAccountDto savingBankAccountDto){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDto,savingAccount);

        return savingAccount;
    }
}
