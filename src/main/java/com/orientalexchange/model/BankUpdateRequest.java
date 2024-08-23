package com.orientalexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankUpdateRequest {
    @NotNull
    private String id;
    @NotNull
    private String bankCode;
    @NotNull
    private String bankName;
    @NotNull
    private String bankAddress;
}
