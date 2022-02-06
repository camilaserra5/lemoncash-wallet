package com.lemoncash.wallet.wallet;

import com.lemoncash.wallet.exception.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @SneakyThrows
    public Wallet getWalletByUserIdAndCurrencyId(Long userId, Long currencyId) {
        return walletRepository.findByUserIdAndCurrencyId(userId, currencyId).orElseThrow(() -> new EntityNotFoundException(String.format("Wallet with userId %s and currencyId %s was not found", userId, currencyId)));
    }

    public Wallet updateWalletAmount(Wallet wallet, Long newAmount) {
        wallet.setAmount(newAmount);
        return walletRepository.save(wallet);
    }
}
