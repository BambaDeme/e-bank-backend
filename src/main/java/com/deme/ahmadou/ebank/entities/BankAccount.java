package com.deme.ahmadou.ebank.entities;

import com.deme.ahmadou.ebank.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",length = 4)
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id
    private String id;

    private double balance;

    private Date createdAt;

    private AccountStatus status;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<AccountOperation> accountOperations;
}
