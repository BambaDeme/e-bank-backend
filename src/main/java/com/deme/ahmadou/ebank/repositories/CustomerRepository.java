package com.deme.ahmadou.ebank.repositories;

import com.deme.ahmadou.ebank.dtos.CustomerDto;
import com.deme.ahmadou.ebank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findCustomersByNameContains(String name);

    @Query("Select c from Customer c where c.name like :name")
    List<Customer> searchCustomer(@Param("name") String name);
}
