package com.finance.creditTransactionms.service;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import com.finance.creditTransactionms.repository.CreditTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditTransactionServiceImpl implements CreditTransactionService{

    @Autowired
    private CreditTransactionRepository creditTransactionRepository;

    @Override
    public Flux<CreditTransaction> findAll() {
        return creditTransactionRepository.findAll();
    }

    @Override
    public Mono<CreditTransaction> findById(String id) {
        return creditTransactionRepository.findById(id);
    }

    @Override
    public Mono<CreditTransaction> save(CreditTransaction creditTransaction) {
        return creditTransactionRepository.save(creditTransaction);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditTransactionRepository.deleteById(id);
    }

    @Override
    public Flux<CreditTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber) {
        return creditTransactionRepository.findByCreditClientDocumentIdentityNumber(documentIdentityNumber);
    }

    @Override
    public Flux<CreditTransaction> findByCreditAccount(String account) {
        return creditTransactionRepository.findByCreditAccount(account);
    }
}
