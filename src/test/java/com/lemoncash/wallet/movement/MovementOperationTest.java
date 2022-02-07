package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.exception.InsufficientFundsException;
import com.lemoncash.wallet.movement.operation.DepositOperation;
import com.lemoncash.wallet.movement.operation.ExtractionOperation;
import com.lemoncash.wallet.movement.operation.MovementOperation;
import com.lemoncash.wallet.movement.operation.MovementOperationStrategy;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementOperationTest {

    private static final Double TEST_AMOUNT = 10D;
    private static final String TEST_CURRENCY_NAME = "USDT";
    private static final Long TEST_USER_ID = 1L;
    private static final Long TEST_CURRENCY_ID = 1L;

    private MovementOperationStrategy movementOperationStrategy;
    private DepositOperation depositOperation;
    private ExtractionOperation extractionOperation;

    @Mock
    private WalletService walletService;

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    public void init() {
        depositOperation = new DepositOperation(walletService, currencyService);
        extractionOperation = new ExtractionOperation(walletService, currencyService);
        movementOperationStrategy = new MovementOperationStrategy(depositOperation, extractionOperation);
    }

    @Test
    public void testMovementOperationStrategyReturnsCorrectMovementOperation() {
        MovementOperation movementOperation = movementOperationStrategy.getMovementOperationByType(Type.deposit);
        assertEquals(depositOperation.getClass().getName(), movementOperation.getClass().getName());

        movementOperation = movementOperationStrategy.getMovementOperationByType(Type.extraction);
        assertEquals(extractionOperation.getClass().getName(), movementOperation.getClass().getName());
    }

    @Test
    public void testMovementOperationHasCorrectType() {
        assertEquals(Type.deposit, depositOperation.getType());
        assertEquals(Type.extraction, extractionOperation.getType());
    }

    @Test
    public void testDepositOperationReturnsCorrectMovement() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setAmount(TEST_AMOUNT);
        movementDTO.setMovementType(Type.deposit);
        movementDTO.setCurrencyName(TEST_CURRENCY_NAME);
        movementDTO.setUserId(TEST_USER_ID);
        Wallet wallet = Wallet.builder().amount(2000D).build();

        when(currencyService.getCurrencyByName(TEST_CURRENCY_NAME)).thenReturn(Currency.builder().id(TEST_CURRENCY_ID).format("%.2f").build());
        when(walletService.getWalletByUserIdAndCurrencyId(TEST_USER_ID, TEST_CURRENCY_ID)).thenReturn(wallet);

        Movement movement = depositOperation.execute(movementDTO);

        assertEquals(Type.deposit, movement.getMovementType());
        assertEquals(2000D + TEST_AMOUNT, movement.getAmount());
        assertEquals(wallet, movement.getWallet());
    }

    @Test
    public void testExtractOperationReturnsCorrectMovement() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setAmount(TEST_AMOUNT);
        movementDTO.setMovementType(Type.extraction);
        movementDTO.setCurrencyName(TEST_CURRENCY_NAME);
        movementDTO.setUserId(TEST_USER_ID);
        Wallet wallet = Wallet.builder().amount(2000D).build();

        when(currencyService.getCurrencyByName(TEST_CURRENCY_NAME)).thenReturn(Currency.builder().id(TEST_CURRENCY_ID).format("%.2f").build());
        when(walletService.getWalletByUserIdAndCurrencyId(TEST_USER_ID, TEST_CURRENCY_ID)).thenReturn(wallet);

        Movement movement = extractionOperation.execute(movementDTO);

        assertEquals(Type.extraction, movement.getMovementType());
        assertEquals(2000D - TEST_AMOUNT, movement.getAmount());
        assertEquals(wallet, movement.getWallet());
    }

    @Test
    public void testExtractOperationWithNoFundsThrowsException() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setAmount(TEST_AMOUNT);
        movementDTO.setMovementType(Type.extraction);
        movementDTO.setCurrencyName(TEST_CURRENCY_NAME);
        movementDTO.setUserId(TEST_USER_ID);
        Wallet wallet = Wallet.builder().amount(0D).id(1L).build();

        when(currencyService.getCurrencyByName(TEST_CURRENCY_NAME)).thenReturn(Currency.builder().id(TEST_CURRENCY_ID).format("%.2f").build());
        when(walletService.getWalletByUserIdAndCurrencyId(TEST_USER_ID, TEST_CURRENCY_ID)).thenReturn(wallet);

        InsufficientFundsException thrown = assertThrows(
                InsufficientFundsException.class,
                () -> extractionOperation.execute(movementDTO),
                "Expected InsufficientFundsException to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Inssufficient funds in wallet 1 to perform extraction"));
    }

}
