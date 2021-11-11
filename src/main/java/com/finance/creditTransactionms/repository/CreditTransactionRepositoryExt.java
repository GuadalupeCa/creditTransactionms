package com.finance.creditTransactionms.repository;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditTransactionRepositoryExt extends ReactiveMongoRepository<CreditTransaction, String > {
    Flux<CreditTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber);
}
