package com.lemoncash.wallet;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyRepository;
import com.lemoncash.wallet.user.User;
import com.lemoncash.wallet.user.UserRepository;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializationDataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public InitializationDataLoader(UserRepository userRepository, WalletRepository walletRepository, CurrencyRepository currencyRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.currencyRepository = currencyRepository;
    }

    public void run(ApplicationArguments args) {
        Currency ars = currencyRepository.save(new Currency(1L, "ARS", "%.2f"));
        Currency usdt = currencyRepository.save(new Currency(2L, "USDT", "%.2f"));
        Currency btc = currencyRepository.save(new Currency(3L, "BTC", "%.8f"));

        User user1 = userRepository.save(new User(1L, "Camila", "Serra", "camilaserra5", "ketchup", "camilaserra5@gmail.com"));
        walletRepository.save(new Wallet(1L, 0D, ars, user1));
        walletRepository.save(new Wallet(1L, 0D, usdt, user1));
        walletRepository.save(new Wallet(1L, 0D, btc, user1));

        User user2 = userRepository.save(new User(2L, "Agustin", "Serra", "agustinagustin", "alioli", "agustin@gmail.com"));
        walletRepository.save(new Wallet(1L, 0D, ars, user2));
        walletRepository.save(new Wallet(1L, 0D, usdt, user2));
        walletRepository.save(new Wallet(1L, 0D, btc, user2));
    }
}
