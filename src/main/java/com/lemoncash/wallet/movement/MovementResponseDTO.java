package com.lemoncash.wallet.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("movementType")
    private Type movementType;

    @JsonProperty("currencyName")
    private String currencyName;

    @JsonProperty("userId")
    private Long userId;
}
