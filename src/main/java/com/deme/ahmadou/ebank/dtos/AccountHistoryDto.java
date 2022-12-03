package com.deme.ahmadou.ebank.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDto {
    private String accountId;
    private double balance;

    private int pageSize;
    private int currentPage;
    private int totalPages;
    private Long totalElements;

    private List<AccountOperationDto> accountOperationDtoList;
}
