package com.lemoncash.wallet.movement.operation;

import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.movement.Movement;
import com.lemoncash.wallet.movement.MovementDTO;
import com.lemoncash.wallet.movement.Type;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public abstract class MovementOperation {
    protected final WalletService walletService;
    protected final CurrencyService currencyService;

    @Autowired
    public MovementOperation(WalletService walletService, CurrencyService currencyService) {
        this.walletService = walletService;
        this.currencyService = currencyService;
    }

    public abstract Movement execute(MovementDTO movementDTO);

    public abstract Type getType();

    protected Movement generateMovement(Double amount, Wallet wallet) {
        Movement movement = new Movement();
        movement.setMovementType(getType());
        movement.setAmount(amount);
        movement.setWallet(wallet);
        movement.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return movement;
    }


}
