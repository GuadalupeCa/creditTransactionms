package com.finance.creditTransactionms.domain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Credit {
    @Id
    private String id;
    private Client client;
    private String account;
    private Product product;
    private Double interestRate;
    private double balance;
    private double lineCredit;
    private double initialLineCredit;
    private String currency;
    private boolean status = true;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cancelAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastMovement;
}
