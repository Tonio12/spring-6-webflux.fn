package com.tonilearnsjava.reactivemongo.web.fn;


import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.tonilearnsjava.reactivemongo.web.fn.CustomerRouterConfig.CUSTOMER_PATH;
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerHandlerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testListCustomers(){
        webTestClient.get()
                .uri(CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CustomerDTO.class);
    }

    @Test
    void testFindCustomerByName(){


        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                .path(CUSTOMER_PATH)
                .queryParam("customerName", "Test")
                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CustomerDTO.class);
    }

    @Test
    void testSaveCustomer(){
        CustomerDTO test = CustomerDTO.builder()
                .customerName("Test Customer")
                .build();

        webTestClient.post()
                .uri(CUSTOMER_PATH)
                .body(Mono.just(test), CustomerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("location");
    }
}