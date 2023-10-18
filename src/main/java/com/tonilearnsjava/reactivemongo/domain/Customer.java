package com.tonilearnsjava.reactivemongo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Customer {
    @Id
    private String id;
   private String customerName;

   @CreatedDate
    private LocalDateTime createdDate;

   @LastModifiedDate
    private LocalDateTime lastDateModified;

}
