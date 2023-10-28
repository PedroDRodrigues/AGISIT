package pt.tecnico.agi.storage.service;

import pt.tecnico.agi.storage.domain.model.Operation;

import java.util.List;

public interface OperationService {
    Operation createOperation(Operation operation);

    List<Operation> getAllOperations();

    void deleteOperation(Operation operation);
}
