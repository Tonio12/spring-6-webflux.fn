package com.tonilearnsjava.reactivemongo.services;

import com.tonilearnsjava.reactivemongo.mappers.CustomerMapper;
import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import com.tonilearnsjava.reactivemongo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Flux<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> saveCustomer(CustomerDTO customerDto) {
        return customerRepository.save(customerMapper.customerDtoToCustomer(customerDto))
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Flux<CustomerDTO> findCustomerByName(String name) {
        return customerRepository.findCustomersByCustomerNameRegex(name)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(CustomerDTO customerDTO,  String customerId) {
        return customerRepository.findById(customerId)
                .map(foundBeer -> {
                    foundBeer.setCustomerName(customerDTO.getCustomerName());
                    return foundBeer;
                })
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDto);
    }

}
