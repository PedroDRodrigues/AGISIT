package pt.tecnico.agi.storage.service;

import pt.tecnico.agi.storage.domain.model.Operation;
import pt.tecnico.agi.storage.domain.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.time.Instant;


@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;


    @Override
    public Operation createOperation(Operation operation) {
        operation.setTimestamp(Instant.now().toEpochMilli());
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAllOperations() {
        Iterator<Operation> iteratorToCollection = operationRepository.findAll().iterator();
        return StreamSupport.stream(
                Spliterators
                        .spliteratorUnknownSize(iteratorToCollection, Spliterator.ORDERED), false)
                .collect(Collectors.toList()
                );
    }

    @Override
    public void deleteOperation(Operation operation) {
        operationRepository.delete(operation);
    }
}
