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
    public class BranchUpdateRequest {
        @NotNull
        private String branchName;
        @NotNull
        private String branchAddress;
    }

