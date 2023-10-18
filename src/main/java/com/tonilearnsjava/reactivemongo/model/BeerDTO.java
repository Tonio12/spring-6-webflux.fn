package com.tonilearnsjava.reactivemongo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDTO {
    private String id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String beerName;

    @NotBlank
    @Size(max = 255)
    private String beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
