package com.finance.creditTransactionms;

import com.finance.creditTransactionms.domain.document.CreditTransaction;
import com.finance.creditTransactionms.handler.CreditTransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FunctionalRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CreditTransactionHandler creditTransactionHandler) {
        return RouterFunctions
                .route(GET("/creditTrans").and(accept(MediaType.APPLICATION_JSON)), creditTransactionHandler::findAll)
                .andRoute(GET("/creditTrans/{id}").and(accept(MediaType.APPLICATION_JSON)), creditTransactionHandler::findById)
                .andRoute(POST("/creditTrans/save").and(accept(MediaType.APPLICATION_JSON)), creditTransactionHandler::save)
                .andRoute(PUT("/creditTrans/update").and(accept(MediaType.APPLICATION_JSON)), creditTransactionHandler::update)
                .andRoute(DELETE("/creditTrans/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), creditTransactionHandler::deleteById);
    }
}
