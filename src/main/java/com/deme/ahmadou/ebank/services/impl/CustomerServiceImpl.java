package com.deme.ahmadou.ebank.services.impl;

import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;
import com.deme.ahmadou.ebank.mappers.CustomerServiceMapperImpl;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import com.deme.ahmadou.ebank.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceMapperImpl customerServiceMapperIml;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        return customerRepository.findAll().stream().map(customerServiceMapperIml::fromCustomer).collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}
