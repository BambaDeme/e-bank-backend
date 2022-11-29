package com.deme.ahmadou.ebank.controllers;


import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
