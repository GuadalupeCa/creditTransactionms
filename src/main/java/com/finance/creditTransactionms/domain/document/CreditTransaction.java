package com.finance.creditTransactionms.domain.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@ToString
@Document(collection = "creditTransaction")
public class CreditTransaction {
    @Id
    private String id;
    private Credit credit;
    private Double amount;
    private TypeTransaction typeTransaction;

    private Integer term;
    private Integer numberFees;
    private String track;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public CreditTransaction(Credit credit, Double amount, TypeTransaction typeTransaction, Integer term, Integer numberFees, String track, Date createAt) {
        this.credit = credit;
        this.amount = amount;
        this.typeTransaction = typeTransaction;
        this.term = term;
        this.numberFees = numberFees;
        this.track = track;
        this.createAt = createAt;
    }
}
