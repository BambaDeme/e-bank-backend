package com.deme.ahmadou.ebank.controllers;


import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;
import com.deme.ahmadou.ebank.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto) throws CustomerNotFoundException{

        customerDto.setId(customerId);
        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException{
        customerService.deleteCustomer(customerId);
    }
}
