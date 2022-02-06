package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.currency.CurrencyService;
import com.lemoncash.wallet.movement.operation.MovementOperation;
import com.lemoncash.wallet.movement.operation.MovementOperationStrategy;
import com.lemoncash.wallet.util.OffsetPagination;
import com.lemoncash.wallet.wallet.Wallet;
import com.lemoncash.wallet.wallet.WalletRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementOperationStrategy movementOperationStrategy;
    private final WalletRepository walletRepository;
    private final CurrencyService currencyService;
    private final MovementMapper movementMapper;

    @Autowired
    public MovementService(MovementRepository movementRepository,
                           MovementOperationStrategy movementOperationStrategy,
                           WalletRepository walletRepository,
                           CurrencyService currencyService,
                           MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.movementOperationStrategy = movementOperationStrategy;
        this.walletRepository = walletRepository;
        this.currencyService = currencyService;
        this.movementMapper = movementMapper;
    }

    @SneakyThrows
    public Movement executeMovement(MovementDTO movementDTO) {
        MovementOperation movementOperation = movementOperationStrategy.getMovementOperationByType(movementDTO.getMovementType());
        if (movementOperation == null)
            throw new Exception();
        Movement movement = movementOperation.execute(movementDTO);
        return this.createMovement(movement);
    }

    public Movement createMovement(Movement movement) {
        return movementRepository.save(movement);
    }

    public List<MovementResponseDTO> listMovements(Long userId, Type movementType, String currencyName, Integer limit, Integer offset) {
        Optional<Currency> optionalCurrency = currencyName != null ? Optional.of(currencyService.getCurrencyByName(currencyName)) : empty();

        List<Movement> movements = new ArrayList<>();
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        if (optionalCurrency.isPresent()) {
            wallets = wallets.stream()
                    .filter(wallet -> optionalCurrency.get().equals(wallet.getCurrency()))
                    .collect(toList());
        }
        for (Wallet wallet : wallets) {
            Pageable pageable = Pageable.unpaged();
            if (limit != null && offset != null) {
                pageable = new OffsetPagination(offset, limit);

            }
            if (movementType != null) {
                movements.addAll(movementRepository.findByWalletIdAndMovementType(wallet.getId(), movementType, pageable));
            } else {
                movements.addAll(movementRepository.findByWalletId(wallet.getId(), pageable));
            }

        }
        return movements.stream().map(movementMapper::movementToMovementResponseDTO).collect(Collectors.toList());
    }
}
