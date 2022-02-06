package com.lemoncash.wallet.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MovementDTO {
    @NotNull
    @JsonProperty("amount")
    private Long amount;

    @NotNull
    @JsonProperty("movementType")
    private Type movementType;

    @NotNull
    @JsonProperty("currencyName")
    private String currencyName;

    @NotNull
    @JsonProperty("userId")
    private Long userId;
}
