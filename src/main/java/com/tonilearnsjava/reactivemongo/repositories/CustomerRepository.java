package com.tonilearnsjava.reactivemongo.repositories;

import com.tonilearnsjava.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Flux<Customer> findCustomersByCustomerNameRegex(String name);
}
