package com.lemoncash.wallet.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserIdAndCurrencyId(Long userId, Long currencyId);

    List<Wallet> findByUserId(Long userId);
}
