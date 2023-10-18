package com.tonilearnsjava.reactivemongo.services;

import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();

    Mono<CustomerDTO> saveCustomer(CustomerDTO customerDto);

    Flux<CustomerDTO> findCustomerByName(String name);
}
