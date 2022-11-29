package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<CustomerDto> listCustomers();

    Customer getCustomerById(Long id) throws CustomerNotFoundException;
}
