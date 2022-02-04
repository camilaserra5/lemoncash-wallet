package com.lemoncash.wallet.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping("/movements")
    public Movement createMovement(@Valid @RequestBody MovementDTO movement) {
        return movementService.createMovement(movement);
    }

    @GetMapping("/movements/{id}")
    public Movement getMovement(@PathVariable Long id) {
        return movementService.getMovementByUserId(id);
    }

}