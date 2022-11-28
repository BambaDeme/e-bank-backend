package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> listCustomers();

    Customer getCustomerById(Long id);
}
