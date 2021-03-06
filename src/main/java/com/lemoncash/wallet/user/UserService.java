package com.lemoncash.wallet.user;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.exception.EntityNotFoundException;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final WalletService walletService;

    private final CurrencyService currencyService;

    @Autowired
    public UserService(UserRepository userRepository, WalletService walletService, CurrencyService currencyService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
        this.currencyService = currencyService;
    }

    @SneakyThrows
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s was not found", id)));
    }

    public User createUser(UserDTO userDTO) {
        User user = userRepository.save(UserMapper.userDTOToUser(userDTO));
        currencyService.getAllCurrencies().forEach(currency -> createWalletWithCurrency(user, currency));
        return user;
    }

    private void createWalletWithCurrency(User user, Currency currency) {
        Wallet wallet = Wallet.builder()
                .amount(0D)
                .currency(currency)
                .user(user).build();
        walletService.createWallet(wallet);
    }

}
