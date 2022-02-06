package com.lemoncash.wallet.movement.operation;

import com.lemoncash.wallet.movement.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovementOperationStrategy {

    private final Map<Type, MovementOperation> movementOperationMap;

    @Autowired
    public MovementOperationStrategy(DepositOperation depositOperation, ExtractionOperation extractionOperation) {
        movementOperationMap = new HashMap<>();
        movementOperationMap.put(depositOperation.getType(), depositOperation);
        movementOperationMap.put(extractionOperation.getType(), extractionOperation);
    }

    public MovementOperation getMovementOperationByType(Type movementType) {
        return movementOperationMap.get(movementType);
    }
}
