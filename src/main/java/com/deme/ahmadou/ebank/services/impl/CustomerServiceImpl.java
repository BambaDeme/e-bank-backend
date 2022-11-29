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
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("Saving new customer {}", customerDto);
        Customer customer = customerServiceMapperIml.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerServiceMapperIml.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        return customerRepository.findAll().stream().map(customerServiceMapperIml::fromCustomer).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) throws CustomerNotFoundException {
       Customer customer =  customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
       return customerServiceMapperIml.fromCustomer(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) throws CustomerNotFoundException {

        log.info("Updating customer {}", customerDto);
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Customer savedCustomer = customerRepository.save(customerServiceMapperIml.fromCustomerDto(customerDto));
        return customerServiceMapperIml.fromCustomer(savedCustomer);

    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        log.info("Deleting customer {}", customer);

        customerRepository.deleteById(customerId);
    }
}
