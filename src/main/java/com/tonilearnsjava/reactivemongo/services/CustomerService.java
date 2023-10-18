package com.tonilearnsjava.reactivemongo.services;

import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();

    Mono<CustomerDTO> saveCustomer(CustomerDTO customerDto);

    Mono<CustomerDTO> findCustomerById(String  customerId);

    Flux<CustomerDTO> findCustomerByName(String name);

    Mono<CustomerDTO> updateCustomer(CustomerDTO customerDTO, String customerId);

}
