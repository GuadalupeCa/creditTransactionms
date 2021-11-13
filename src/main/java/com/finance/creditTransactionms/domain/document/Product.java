package com.finance.creditTransactionms.domain.document;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String type;

    private Boolean commissionMaintenance;
    private Integer amountCommissionMaintenance;

    private Boolean maximumLimitMonthlyMovements;
    private Integer quantityMaximumLimitMonthlyMovements;

    private Boolean allowDeposit;
    private Boolean allowWithdrawal;

    private Integer maximumQuantityCredit;
    private Integer maximumQuantityDebit;
}

