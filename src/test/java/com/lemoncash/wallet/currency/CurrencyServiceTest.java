package com.lemoncash.wallet.currency;

import com.lemoncash.wallet.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

    private static final Long TEST_ID = 1L;
    private static final Long ANOTHER_TEST_ID = 2L;
    private static final String TEST_CURRENCY_NAME = "USDT";
    private static final String TEST_CURRENCY_FORMAT = "";

    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @BeforeEach
    public void init() {
        currencyService = new CurrencyService(currencyRepository);
    }

    @Test
    public void testGetCurrencyByNameReturnCorrectCurrency() {
        Currency currency = Currency.builder()
                .id(TEST_ID).format(TEST_CURRENCY_FORMAT)
                .name(TEST_CURRENCY_NAME).build();
        Mockito.when(currencyRepository.findByName(currency.getName())).thenReturn(Optional.of(currency));

        Currency returnedCurrency = currencyService.getCurrencyByName(TEST_CURRENCY_NAME);

        assertEquals(TEST_CURRENCY_NAME, returnedCurrency.getName());
        assertEquals(TEST_ID, returnedCurrency.getId());
        assertEquals(TEST_CURRENCY_FORMAT, returnedCurrency.getFormat());
    }

    @Test
    public void testGetCurrencyByUnknownNameThrowsException() {
        Currency currency = Currency.builder()
                .id(TEST_ID).format(TEST_CURRENCY_FORMAT)
                .name(TEST_CURRENCY_NAME).build();
        Mockito.when(currencyRepository.findByName(currency.getName())).thenReturn(Optional.of(currency));

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> currencyService.getCurrencyByName("UNKNOWN"),
                "Expected EntityNotFoundException to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Currency UNKNOWN was not found"));
    }

    @Test
    public void testGetAllCurrenciesReturnsAll() {
        Currency currencyOne = Currency.builder()
                .id(TEST_ID).format(TEST_CURRENCY_FORMAT)
                .name(TEST_CURRENCY_NAME).build();
        Currency currencyTwo = Currency.builder()
                .id(ANOTHER_TEST_ID).format(TEST_CURRENCY_FORMAT)
                .name(TEST_CURRENCY_NAME).build();
        Mockito.when(currencyRepository.findAll()).thenReturn(List.of(currencyOne, currencyTwo));

        List<Currency> currencies = currencyService.getAllCurrencies();

        assertEquals(2, currencies.size());
        assertThat(currencies, contains(
                hasProperty("id", is(TEST_ID)),
                hasProperty("id", is(ANOTHER_TEST_ID))
        ));
    }

}
