package com.lemoncash.wallet.currency;

import com.lemoncash.wallet.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyByName(String currencyName) {
        return currencyRepository.findByName(currencyName).orElseThrow(() -> new EntityNotFoundException(String.format("Currency %s was not found", currencyName)));

    }
}
