package com.deme.ahmadou.ebank.mappers;


import com.deme.ahmadou.ebank.dtos.AccountOperationDto;
import com.deme.ahmadou.ebank.entities.AccountOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationServiceMapperImpl {

    public AccountOperationDto fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDto accountOperationDto = new AccountOperationDto();
        BeanUtils.copyProperties(accountOperation,accountOperationDto);

        return accountOperationDto;
    }

    public AccountOperation fromAccountOperationDto(AccountOperationDto accountOperationDto){
        AccountOperation accountOperation = new AccountOperation();
        BeanUtils.copyProperties(accountOperationDto,accountOperation);

        return accountOperation;
    }
}
