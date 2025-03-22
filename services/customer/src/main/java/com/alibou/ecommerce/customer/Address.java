package com.alibou.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;

}
