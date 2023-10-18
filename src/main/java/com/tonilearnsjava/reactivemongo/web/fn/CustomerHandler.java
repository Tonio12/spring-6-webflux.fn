package com.tonilearnsjava.reactivemongo.web.fn;

import com.tonilearnsjava.reactivemongo.model.CustomerDTO;
import com.tonilearnsjava.reactivemongo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.tonilearnsjava.reactivemongo.web.fn.CustomerRouterConfig.CUSTOMER_PATH_ID;


@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerService customerService;

    private final Validator validator;

    private void validate(CustomerDTO dto) {
        Errors errors = new BeanPropertyBindingResult(dto, "dto");

        validator.validate(dto, errors);

        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }


    public Mono<ServerResponse> listCustomers(ServerRequest request){
        Flux<CustomerDTO> flux;

        if(request.queryParam("customerName").isPresent()){
            flux = customerService.findCustomerByName(request.queryParam("customerName").get());
        }else{
            flux = customerService.listCustomers();
        }

        return ServerResponse.ok()
                .body(flux,  CustomerDTO.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        return  request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerService::saveCustomer)
                .flatMap(dto -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath(CUSTOMER_PATH_ID)
                                .build(dto.getId()))
                        .build());
    }


}
