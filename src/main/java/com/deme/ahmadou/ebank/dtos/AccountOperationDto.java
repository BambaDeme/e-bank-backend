package com.deme.ahmadou.ebank.dtos;

import com.deme.ahmadou.ebank.entities.BankAccount;
import com.deme.ahmadou.ebank.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
public class AccountOperationDto {

    private Long id;

    private Date operationDate;

    private double amount;

    private OperationType type;

    private String description;


}
