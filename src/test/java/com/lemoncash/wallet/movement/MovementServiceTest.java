package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.exception.UnrecognizedMovementType;
import com.lemoncash.wallet.movement.operation.DepositOperation;
import com.lemoncash.wallet.movement.operation.MovementOperationStrategy;
import com.lemoncash.wallet.wallet.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementServiceTest {

    private MovementService movementService;

    @Mock
    private MovementRepository movementRepository;
    @Mock
    private MovementOperationStrategy movementOperationStrategy;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private CurrencyService currencyService;

    @Mock
    private DepositOperation depositOperation;

    @BeforeEach
    public void init() {
        movementService = new MovementService(movementRepository, movementOperationStrategy, walletRepository, currencyService);
    }

    @Test
    public void testGetCurrencyByNameReturnCorrectCurrency() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setMovementType(Type.deposit);

        when(movementOperationStrategy.getMovementOperationByType(Type.deposit)).thenReturn(null);

        UnrecognizedMovementType thrown = assertThrows(
                UnrecognizedMovementType.class,
                () -> movementService.executeMovement(movementDTO),
                "Expected UnrecognizedMovementType to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Movement type 'deposit' does not have a movement operation implemented"));
    }

    @Test
    public void testGetCurrencyBdyNameReturnCorrectCurrency() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setMovementType(Type.deposit);
        Movement movement = new Movement();

        when(movementOperationStrategy.getMovementOperationByType(Type.deposit)).thenReturn(depositOperation);
        when(depositOperation.execute(movementDTO)).thenReturn(movement);

        movementService.executeMovement(movementDTO);

        verify(movementRepository, times(1)).save(movement);
    }
}
