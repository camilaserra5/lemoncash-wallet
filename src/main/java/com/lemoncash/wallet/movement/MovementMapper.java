package com.lemoncash.wallet.movement;

import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public Movement movementDTOToMovement(MovementDTO movementDTO) {
        if (movementDTO == null) {
            return null;
        }
        Movement movement = new Movement();
        movement.setAmount(movementDTO.getAmount());
        movement.setMovementType(movementDTO.getMovementType());
        return movement;
    }
}
