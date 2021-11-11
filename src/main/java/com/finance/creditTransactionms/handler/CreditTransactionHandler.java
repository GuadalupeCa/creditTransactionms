package com.finance.creditTransactionms.handler;

import com.finance.creditTransactionms.domain.document.Credit;
import com.finance.creditTransactionms.domain.document.CreditTransaction;
import com.finance.creditTransactionms.service.CreditTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CreditTransactionHandler {
    @Autowired
    private CreditTransactionService creditTransactionService;

    public Mono findAll(ServerRequest serverRequest) {
        log.info("Find all clients");
        return ServerResponse.ok()
                .body(creditTransactionService.findAll(), CreditTransaction.class);
    }

    public Mono findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        log.info("Find by Id: {}", id);
        return ServerResponse.ok().body(creditTransactionService.findById(id), CreditTransaction.class);
    }

    public Mono save(ServerRequest serverRequest) {
        Mono<CreditTransaction> credit = serverRequest.bodyToMono(CreditTransaction.class);
        log.info("Update credit");
        return credit.flatMap(p -> ServerResponse
                .status(HttpStatus.CREATED)
                .body(creditTransactionService.save(p), CreditTransaction.class));
    }

    public Mono update(ServerRequest serverRequest) {
        Mono<CreditTransaction> credit = serverRequest.bodyToMono(CreditTransaction.class);
        log.info("Update credit");
        return credit.flatMap(p -> ServerResponse
                .status(HttpStatus.CREATED)
                .body(creditTransactionService.save(p), CreditTransaction.class));
    }

    public Mono deleteById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        log.info("delete credit by id");
        return creditTransactionService.deleteById(id).then(ServerResponse.noContent().build());
    }
}
