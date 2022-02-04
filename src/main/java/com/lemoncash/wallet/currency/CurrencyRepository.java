package com.lemoncash.wallet.currency;

import com.lemoncash.wallet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
