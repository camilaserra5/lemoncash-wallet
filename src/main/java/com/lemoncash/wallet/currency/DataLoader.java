package com.lemoncash.wallet.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public DataLoader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void run(ApplicationArguments args) {
        currencyRepository.save(new Currency(1L, "ARS", ""));
        currencyRepository.save(new Currency(2L, "USDT", ""));
        currencyRepository.save(new Currency(3L, "BTC", ""));
    }
}
