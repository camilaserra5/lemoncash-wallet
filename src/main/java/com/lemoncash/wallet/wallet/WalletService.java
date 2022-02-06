package com.lemoncash.wallet.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Wallet getWalletByUserIdAndCurrencyId(Long userId, Long currencyId) throws Exception {
        List<Wallet> walletList = walletRepository.findByUserIdAndCurrencyId(userId, currencyId);
        if (walletList.size() != 1)
            throw new Exception();
        return walletList.get(0);
    }

    public Wallet updateWalletAmount(Wallet wallet, Long newAmount) {
        wallet.setAmount(newAmount);
        return walletRepository.save(wallet);
    }
}
