package com.lemoncash.wallet.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MovementDTO {
    @NotNull
    @Min(value = 0)
    @JsonProperty("amount")
    private Double amount;

    @NotNull
    @JsonProperty("movementType")
    private Type movementType;

    @NotNull
    @NotEmpty
    @JsonProperty("currencyName")
    private String currencyName;

    @NotNull
    @JsonProperty("userId")
    private Long userId;
}
