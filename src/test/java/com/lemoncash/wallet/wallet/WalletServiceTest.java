package com.lemoncash.wallet.wallet;

import com.lemoncash.wallet.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceTest {
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    @BeforeEach
    public void init() {
        walletService = new WalletService(walletRepository);
    }

    @Test
    public void testGetWalletByUnknownUserIdAndCurrencyIdThrowsException() {
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> walletService.getWalletByUserIdAndCurrencyId(1L, 1L),
                "Expected EntityNotFoundException to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Wallet with userId 1 and currencyId 1 was not found"));
    }

    @Test
    public void testGetWalletByUserIdAndCurrencyIdReturnsCorrespondingWallet() {
        Wallet testWallet = Wallet.builder().build();

        when(walletRepository.findByUserIdAndCurrencyId(1L, 1L)).thenReturn(Optional.ofNullable(testWallet));

        Wallet wallet = walletService.getWalletByUserIdAndCurrencyId(1L, 1L);

        assertEquals(testWallet, wallet);
    }

}
