package com.finance.creditTransactionms.repository.impl;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import com.finance.creditTransactionms.repository.CreditTransactionRepository;
import com.finance.creditTransactionms.repository.CreditTransactionRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CreditTransactionRepositoryImpl implements CreditTransactionRepository {

    @Autowired
    private CreditTransactionRepositoryExt creditTransactionRepositoryExt;

    @Override
    public Flux<CreditTransaction> findAll() {
        return creditTransactionRepositoryExt.findAll();
    }

    @Override
    public Mono<CreditTransaction> findById(String id) {
        return creditTransactionRepositoryExt.findById(id);
    }

    @Override
    public Mono<CreditTransaction> save(CreditTransaction creditTransaction) {
        return creditTransactionRepositoryExt.save(creditTransaction);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditTransactionRepositoryExt.deleteById(id);
    }

    @Override
    public Flux<CreditTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber) {
        return creditTransactionRepositoryExt.findByCreditClientDocumentIdentityNumber(documentIdentityNumber);
    }
}
