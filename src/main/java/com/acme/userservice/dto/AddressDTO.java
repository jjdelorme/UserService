package com.acme.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AddressDTO {
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}