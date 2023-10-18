package com.tonilearnsjava.reactivemongo.web.fn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Configuration
public class CustomerRouterConfig {


    public static final String CUSTOMER_PATH = "/api/v3/customer";
    public static final String CUSTOMER_PATH_ID =  CUSTOMER_PATH + "/{customerId}";

    private final CustomerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> customerRoutes(){
        return route()
                .GET(CUSTOMER_PATH, accept(MediaType.APPLICATION_JSON), handler::listCustomers)
                .POST(CUSTOMER_PATH, accept(MediaType.APPLICATION_JSON), handler::saveCustomer)
                .build();
    }
}
