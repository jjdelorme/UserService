package com.acme.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DivisionDTO {
    private Long divisionId;
    private String divisionName;
    private String description;
}
