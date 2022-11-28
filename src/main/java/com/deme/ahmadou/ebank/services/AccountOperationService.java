package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.entities.Customer;

import java.util.List;

public interface AccountOperationService {

    void debit(String accountId, double amount, String description);

    void credit (String accountId, double amount, String description);

    void transfer(String accountIdSource, String accountIdDestination, double amount);
}
