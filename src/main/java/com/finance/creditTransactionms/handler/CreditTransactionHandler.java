package com.finance.creditTransactionms.handler;

import com.finance.creditTransactionms.domain.document.Credit;
import com.finance.creditTransactionms.domain.document.CreditTransaction;
import com.finance.creditTransactionms.service.CreditTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public Mono saveCharge(ServerRequest serverRequest) {
        Map<String, Object> response = new HashMap<>();
        Mono<CreditTransaction> creditTransaction = serverRequest.bodyToMono(CreditTransaction.class);
        log.info("Update credit");

        Mono<Credit> creditData = creditTransaction.flatMap(d -> WebClient.builder().build().get()
                .uri("http://localhost:8080/credit/account/{account}", d.getCredit().getAccount())
                .retrieve()
                .bodyToMono(Credit.class));

        return creditTransaction.flatMap(charge ->
                creditData.flatMap( credit -> {
                    if (charge.getAmount() >= credit.getLineCredit()) {
                        credit.setBalance( credit.getLineCredit() - charge.getAmount());
                        credit.setLastMovement(new Date());
                        return creditTransaction.flatMap(d -> WebClient.builder().build().post()
                                        .uri("http://localhost:8080/credit/update")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(credit)
                                        .retrieve()
                                        .bodyToMono(Credit.class))
                                .then(ServerResponse
                                        .status(HttpStatus.CREATED)
                                        .body(creditTransactionService.save(charge), CreditTransaction.class));
                    }
                    else {
                        response.put("Message", "No cuenta con suficinete linea de credito.");
                        return ServerResponse.status(HttpStatus.NO_CONTENT)
                                .body(BodyInserters.fromValue(response));
                    }
                })
        );
    }

    public Mono savePay(ServerRequest serverRequest) {
        Map<String, Object> response = new HashMap<>();

        Mono<CreditTransaction> creditTransaction = serverRequest.bodyToMono(CreditTransaction.class);
        log.info("Save withdraw");

        Mono<Credit> creditData = creditTransaction.flatMap(d -> WebClient.builder().build().get()
                .uri("http://localhost:8080/credit/account/{account}", d.getCredit().getAccount())
                .retrieve()
                .bodyToMono(Credit.class));

        return creditTransaction.flatMap(pay ->
                creditData.flatMap( credit -> {
                    credit.setBalance( credit.getLineCredit() + pay.getAmount());
                    credit.setLastMovement(new Date());
                    return creditTransaction.flatMap(d -> WebClient.builder().build().post()
                                    .uri("http://localhost:8080/credit/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(credit)
                                    .retrieve()
                                    .bodyToMono(Credit.class))
                            .then(ServerResponse
                                    .status(HttpStatus.CREATED)
                                    .body(creditTransactionService.save(pay), CreditTransaction.class));

                })
        );
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

    public Mono findByCreditAccount (ServerRequest serverRequest) {
        String account = serverRequest.pathVariable("account");
        log.info("Find by account: {}", account);
        return ServerResponse.ok().body(creditTransactionService.findByCreditAccount(account), CreditTransaction.class);
    }
}
