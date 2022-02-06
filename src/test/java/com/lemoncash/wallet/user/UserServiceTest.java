package com.lemoncash.wallet.user;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.exception.EntityNotFoundException;
import com.lemoncash.wallet.wallet.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository, walletService, currencyService);
    }

    @Test
    public void testFindUserByIdWithUnknownIdThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> userService.getUserById(1L),
                "Expected EntityNotFoundException to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("User with id 1 was not found"));
    }

    @Test
    public void testGetUserByIdReturnsCorrectUser() {
        User testUser = User.builder()
                .id(1L)
                .firstName("alioli")
                .build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User user = userService.getUserById(1L);

        assertEquals(testUser, user);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUserCreatesCorrespondingWallets() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password");
        User testUser = User.builder()
                .id(1L)
                .firstName("alioli")
                .build();

        when(userRepository.save(any())).thenReturn(testUser);
        when(currencyService.getAllCurrencies()).thenReturn(List.of(new Currency(), new Currency()));

        User user = userService.createUser(userDTO);

        assertEquals(testUser, user);
        verify(walletService, times(2)).createWallet(any());
    }
}
