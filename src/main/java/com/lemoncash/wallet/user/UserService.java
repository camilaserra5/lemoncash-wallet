package com.lemoncash.wallet.user;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(UserDTO userDTO) {
        User user = userRepository.save(userMapper.userDTOToUser(userDTO));
        for (Currency currency: currencyService.getAllCurrencies()) {
            Wallet wallet = new Wallet();
            wallet.setAmount(0L);
            wallet.setCurrency(currency);
            wallet.setUser(user);
            walletService.createWallet(wallet);
        }
        return user;
    }

}
