package com.lemoncash.wallet.movement.operation;

import com.lemoncash.wallet.movement.Movement;
import com.lemoncash.wallet.movement.MovementDTO;
import com.lemoncash.wallet.movement.Type;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class DepositOperation extends MovementOperation {

    public DepositOperation(WalletService walletService) {
        super(walletService);
    }

    @SneakyThrows
    @Override
    public Movement execute(MovementDTO movementDTO) {
        Wallet wallet = walletService.getWalletByUserIdAndCurrencyId(movementDTO.getUserId(), movementDTO.getCurrencyId());
        long newAmount = wallet.getAmount() + movementDTO.getAmount();
        walletService.updateWalletAmount(wallet, newAmount);
        return generateMovement(movementDTO.getAmount(), wallet);
    }

    @Override
    public Type getType() {
        return Type.deposit;
    }
}
