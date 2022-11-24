package com.deme.ahmadou.ebank.repositories;

import com.deme.ahmadou.ebank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
