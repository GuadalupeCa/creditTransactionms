package com.finance.creditTransactionms.service;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditTransactionService {
    Flux<CreditTransaction> findAll();
    Mono<CreditTransaction> findById(String id);
    Mono<CreditTransaction> save(CreditTransaction creditTransaction);
    Mono<Void> deleteById(String id);

    Flux<CreditTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber);
}
