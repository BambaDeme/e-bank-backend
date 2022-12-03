package com.deme.ahmadou.ebank.controllers;


import com.deme.ahmadou.ebank.dtos.BankAccountDto;
import com.deme.ahmadou.ebank.dtos.CurrentBankAccountDto;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor

public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccountDto> getBankAccounts(){
        return bankAccountService.getBankAccountList();
    }

    @GetMapping("/{id}")
    public BankAccountDto getBankAccount(@PathVariable(name = "id") String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    /*@PostMapping("/currentAccount")
    public CurrentBankAccountDto saveCurrentBankAccount(@RequestBody )*/
}
