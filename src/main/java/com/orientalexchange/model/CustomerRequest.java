package com.orientalexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {
    @NotNull
    private String customerName;
    @NotNull
    private String customerAddress;
}
