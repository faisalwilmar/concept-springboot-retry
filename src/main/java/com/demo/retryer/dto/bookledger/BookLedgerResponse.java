package com.demo.retryer.dto.bookledger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLedgerResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("codeMessage")
    private String codeMessage;
}
