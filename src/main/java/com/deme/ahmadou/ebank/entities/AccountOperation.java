package com.deme.ahmadou.ebank.entities;

import com.deme.ahmadou.ebank.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date operationDate;

    private double amount;

    private OperationType type;

    @ManyToOne
    private BankAccount account;
}
