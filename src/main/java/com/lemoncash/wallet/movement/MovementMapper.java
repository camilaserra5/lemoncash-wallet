package com.lemoncash.wallet.movement;

import org.springframework.stereotype.Component;

public class MovementMapper {

    public static MovementResponseDTO movementToMovementResponseDTO(Movement movement) {
        if (movement == null) {
            return null;
        }

        return MovementResponseDTO.builder()
                .movementType(movement.getMovementType())
                .amount(movement.getAmount())
                .currencyName(movement.getWallet().getCurrency().getName())
                .userId(movement.getWallet().getUser().getId())
                .id(movement.getId()).build();
    }
}
