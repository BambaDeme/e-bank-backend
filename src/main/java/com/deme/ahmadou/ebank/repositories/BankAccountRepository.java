package com.deme.ahmadou.ebank.repositories;

import com.deme.ahmadou.ebank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
