package com.deme.ahmadou.ebank.repositories;

import com.deme.ahmadou.ebank.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    public List<AccountOperation> findAccountOperationByAccountId(String accountId);

    public Page<AccountOperation> findAccountOperationByAccountId(String accountId, Pageable pageable);
}
