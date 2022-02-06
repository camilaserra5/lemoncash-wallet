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

import static java.util.stream.Collectors.toList;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementOperationStrategy movementOperationStrategy;
    private final WalletRepository walletRepository;
    private final CurrencyService currencyService;

    @Autowired
    public MovementService(MovementRepository movementRepository,
                           MovementOperationStrategy movementOperationStrategy,
                           WalletRepository walletRepository,
                           CurrencyService currencyService) {
        this.movementRepository = movementRepository;
        this.movementOperationStrategy = movementOperationStrategy;
        this.walletRepository = walletRepository;
        this.currencyService = currencyService;
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
        List<Movement> movements = new ArrayList<>();
        List<Wallet> wallets = walletRepository.findByUserId(userId);

        wallets = filterWalletsByOptionalCurrency(wallets, Optional.ofNullable(currencyName));

        Pageable pageable = getPageable(limit, offset);
        wallets.forEach(wallet -> movements.addAll(movementRepository.findByWalletIdAndOptionalMovementType(wallet.getId(), Optional.ofNullable(movementType), pageable)));
        return movements.stream().map(MovementMapper::movementToMovementResponseDTO).collect(Collectors.toList());
    }

    private Pageable getPageable(Integer limit, Integer offset) {
        Pageable pageable = Pageable.unpaged();
        if (limit != null && offset != null) {
            pageable = new OffsetPagination(offset, limit);
        }
        return pageable;
    }

    private List<Wallet> filterWalletsByOptionalCurrency(List<Wallet> wallets, Optional<String> currencyName) {
        if (currencyName.isEmpty()) {
            return wallets;
        }

        Currency currency = currencyService.getCurrencyByName(currencyName.get());
        return wallets.stream()
                .filter(wallet -> currency.equals(wallet.getCurrency()))
                .collect(toList());
    }
}
