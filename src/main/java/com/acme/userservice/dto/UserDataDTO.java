package com.acme.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserDataDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private DivisionDTO division;
}
