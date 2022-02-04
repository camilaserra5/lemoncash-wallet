package com.lemoncash.wallet.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private MovementMapper movementMapper;


    public Movement createMovement(MovementDTO movement) {
        return movementRepository.save(movementMapper.movementDTOToMovement(movement));
    }

    public Movement getMovementByUserId(Long id) {
        return movementRepository.findById(id).get();
    }
}
