package com.lemoncash.wallet.movement;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @SneakyThrows
    @PostMapping("/movements")
    public ResponseEntity<String> createMovement(@Valid @RequestBody MovementDTO movementDTO) {
        Movement movement = movementService.executeMovement(movementDTO);
        return ResponseEntity.ok(String.format("Movement %s performed", movement.getId()));
    }

    @GetMapping("/movements")
    public List<MovementResponseDTO> getMovements(@RequestParam(name = "user_id") Long userId,
                                                  @RequestParam(name = "movement_type", required = false) Type movementType,
                                                  @RequestParam(name = "currency_name", required = false) String currencyName,
                                                  @RequestParam(name = "limit", required = false) Integer limit,
                                                  @RequestParam(name = "offset", required = false) Integer offset) {
        return movementService.listMovements(userId, movementType, currencyName, limit, offset);
    }

}
