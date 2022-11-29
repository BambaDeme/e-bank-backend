package com.deme.ahmadou.ebank.mappers;

import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceMapperImpl {
    public CustomerDto fromCustomer(Customer customer){

        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);

        return customerDto;
    }

    public Customer fromCustomerDto(CustomerDto customerDto){

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);

        return customer;
    }
}
