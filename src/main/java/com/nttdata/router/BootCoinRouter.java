package com.nttdata.router;


import com.nttdata.handler.BootCoinHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BootCoinRouter {
    @Bean
    public RouterFunction<ServerResponse> transactionyankiRouterFunc(BootCoinHandler transactionHandler) {
        return RouterFunctions.route(GET("/salebootcoin").and(accept(MediaType.APPLICATION_JSON)), transactionHandler::findBootCoin);


    }

}
