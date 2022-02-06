package com.lemoncash.wallet.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findByUserIdAndCurrencyId(Long userId, Long currencyId);

    List<Wallet> findByUserId(Long userId);
}
