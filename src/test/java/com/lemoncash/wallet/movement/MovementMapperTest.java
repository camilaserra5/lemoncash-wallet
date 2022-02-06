package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.user.User;
import com.lemoncash.wallet.wallet.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class MovementMapperTest {


    @Test
    public void testMovementMapperWithNullMovementReturnsNull() {
        assertNull(MovementMapper.movementToMovementResponseDTO(null));
    }

    @Test
    public void testMovementMapperReturnsCorrectDTO() {
        Type movementType = Type.deposit;
        Long amount = 100L;
        String currencyName = "ARS";
        Long userId = 1L;
        Long movementId = 10L;

        Wallet wallet = Wallet.builder()
                .currency(Currency.builder().name(currencyName).build())
                .user(User.builder().id(userId).build()).build();
        Movement movement = Movement.builder()
                .movementType(movementType).amount(amount)
                .wallet(wallet).id(movementId).build();

        MovementResponseDTO movementResponseDTO = MovementMapper.movementToMovementResponseDTO(movement);

        assertEquals(movementType, movementResponseDTO.getMovementType());
        assertEquals(amount, movementResponseDTO.getAmount());
        assertEquals(currencyName, movementResponseDTO.getCurrencyName());
        assertEquals(userId, movementResponseDTO.getUserId());
        assertEquals(movementId, movementResponseDTO.getId());
    }
}
