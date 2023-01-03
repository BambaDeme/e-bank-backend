package com.deme.ahmadou.ebank.services;

import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);

    List<CustomerDto> listCustomers();

    CustomerDto getCustomerById(Long id) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customerDto) throws CustomerNotFoundException;

    void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    List<CustomerDto> searchCustomers(String name);
}
