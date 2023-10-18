package com.tonilearnsjava.reactivemongo.mappers;

import com.tonilearnsjava.reactivemongo.domain.Beer;
import com.tonilearnsjava.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDTO dto);
}
