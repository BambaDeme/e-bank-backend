package com.deme.ahmadou.ebank.repositories;

import com.deme.ahmadou.ebank.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
