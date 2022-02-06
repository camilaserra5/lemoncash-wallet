package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.movement.operation.MovementOperation;
import com.lemoncash.wallet.movement.operation.MovementOperationStrategy;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementOperationStrategy movementOperationStrategy;
    private final WalletRepository walletRepository;

    @Autowired
    public MovementService(MovementRepository movementRepository, MovementOperationStrategy movementOperationStrategy, WalletRepository walletRepository) {
        this.movementRepository = movementRepository;
        this.movementOperationStrategy = movementOperationStrategy;
        this.walletRepository = walletRepository;
    }

    public Movement executeMovement(MovementDTO movementDTO) throws Exception {
        MovementOperation movementOperation = movementOperationStrategy.getMovementOperationByType(movementDTO.getMovementType());
        if (movementOperation == null)
            throw new Exception();
        Movement movement = movementOperation.execute(movementDTO);
        return movementRepository.save(movement);
    }

    public Movement createMovement(Movement movement) {
        return movementRepository.save(movement);
    }

    public List<Movement> getMovementByUserId(Long userId) {
        List<Movement> movements = new ArrayList<>();
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        for (Wallet wallet : wallets) {
            movements.addAll(movementRepository.findByWalletId(wallet.getId()));
        }
        return movements;
    }
}
