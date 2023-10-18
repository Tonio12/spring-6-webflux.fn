package com.tonilearnsjava.reactivemongo.mappers;

import com.tonilearnsjava.reactivemongo.domain.Customer;
import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDto(Customer customer);

}
