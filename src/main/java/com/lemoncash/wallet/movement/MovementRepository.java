package com.lemoncash.wallet.movement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovementRepository extends PagingAndSortingRepository<Movement, Long> {

    List<Movement> findByWalletId(Long walletId, Pageable pageable);

    List<Movement> findByWalletIdAndMovementType(Long walletId, Type movementType, Pageable pageable);
}
