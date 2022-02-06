package com.lemoncash.wallet.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByWalletId(Long walletId);
}
