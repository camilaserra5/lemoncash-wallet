package com.lemoncash.wallet.movement.operation;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.exception.InsufficientFundsException;
import com.lemoncash.wallet.movement.Movement;
import com.lemoncash.wallet.movement.MovementDTO;
import com.lemoncash.wallet.movement.Type;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ExtractionOperation extends MovementOperation {

    public ExtractionOperation(WalletService walletService, CurrencyService currencyService) {
        super(walletService, currencyService);
    }

    @SneakyThrows
    @Override
    public Movement execute(MovementDTO movementDTO) {
        Currency currency = currencyService.getCurrencyByName(movementDTO.getCurrencyName());
        Wallet wallet = walletService.getWalletByUserIdAndCurrencyId(movementDTO.getUserId(), currency.getId());
        Double newAmount = wallet.getAmount() - movementDTO.getAmount();
        if (newAmount < 0)
            throw new InsufficientFundsException(String.format("Inssufficient funds in wallet %s to perform extraction", wallet.getId()));
        Double formattedAmount = Double.valueOf(String.format(currency.getFormat(), newAmount));
        walletService.updateWalletAmount(wallet, formattedAmount);
        return generateMovement(formattedAmount, wallet);
    }

    @Override
    public Type getType() {
        return Type.extraction;
    }
}
