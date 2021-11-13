package com.finance.creditTransactionms.repository;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditTransactionRepository {
    Flux<CreditTransaction> findAll();
    Mono<CreditTransaction> findById(String id);
    Mono<CreditTransaction> save(CreditTransaction credit);
    Mono<Void> deleteById(String id);
    Flux<CreditTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber);
    Flux<CreditTransaction> findByCreditAccount(String account);
}
