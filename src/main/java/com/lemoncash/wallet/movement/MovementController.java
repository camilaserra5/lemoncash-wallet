package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovementController {

    private final MovementService movementService;

    @Autowired
    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping("/movements")
    public Movement createMovement(@Valid @RequestBody MovementDTO movement) throws Exception {
        return movementService.executeMovement(movement);
    }

    @GetMapping("/movements")
    public List<Movement> getMovements(@RequestParam(name="user_id") Long userId,
                                       @RequestParam(name = "movement_type", required = false) Type movementType,
                                       @RequestParam(name="currency_name", required = false) String currencyName,
                                       @RequestParam(name="limit", required = false) Integer limit,
                                       @RequestParam(name="offset", required = false) Integer offset) {
        return movementService.listMovements(userId, movementType, currencyName, limit, offset);
    }

}
