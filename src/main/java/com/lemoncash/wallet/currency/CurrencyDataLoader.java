package com.lemoncash.wallet.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDataLoader implements ApplicationRunner {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyDataLoader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void run(ApplicationArguments args) {
        currencyRepository.save(new Currency(1L, "ARS", "%.2f"));
        currencyRepository.save(new Currency(2L, "USDT", "%.2f"));
        currencyRepository.save(new Currency(3L, "BTC", "%.8f"));
    }
}
