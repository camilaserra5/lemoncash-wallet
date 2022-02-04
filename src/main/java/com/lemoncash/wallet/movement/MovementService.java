package com.lemoncash.wallet.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;

    @Autowired
    public MovementService(MovementRepository movementRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
    }

    public Movement createMovement(MovementDTO movement) {
        return movementRepository.save(movementMapper.movementDTOToMovement(movement));
    }

    public Movement getMovementByUserId(Long id) {
        return movementRepository.findById(id).get();
    }
}
