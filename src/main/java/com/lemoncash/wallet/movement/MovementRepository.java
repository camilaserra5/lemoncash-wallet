package com.lemoncash.wallet.movement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovementRepository extends PagingAndSortingRepository<Movement, Long> {

    List<Movement> findByWalletId(Long walletId, Pageable pageable);

    List<Movement> findByWalletIdAndMovementType(Long walletId, Type movementType, Pageable pageable);

    default List<Movement> findByWalletIdAndOptionalMovementType(Long walletId, Optional<Type> optionalMovementType, Pageable pageable) {
        return (optionalMovementType.isEmpty()) ? this.findByWalletId(walletId, pageable) : this.findByWalletIdAndMovementType(walletId, optionalMovementType.get(), pageable);
    }

}
