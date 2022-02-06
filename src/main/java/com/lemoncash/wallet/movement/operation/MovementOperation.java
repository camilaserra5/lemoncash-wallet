package com.lemoncash.wallet.movement.operation;

import com.lemoncash.wallet.movement.Movement;
import com.lemoncash.wallet.movement.MovementDTO;
import com.lemoncash.wallet.movement.Type;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MovementOperation {
    protected WalletService walletService;

    @Autowired
    public MovementOperation(WalletService walletService) {
        this.walletService = walletService;
    }

    public abstract Movement execute(MovementDTO movementDTO) throws Exception;

    public abstract Type getType();

    protected Movement generateMovement(Long amount, Wallet wallet) {
        Movement movement = new Movement();
        movement.setMovementType(getType());
        movement.setAmount(amount);
        movement.setWallet(wallet);
        return movement;
    }


}
