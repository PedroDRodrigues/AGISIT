package pt.tecnico.agi.storage.controller;

import lombok.extern.slf4j.Slf4j;
import pt.tecnico.agi.storage.domain.model.Operation;
import pt.tecnico.agi.storage.service.OperationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/storage")
@Slf4j
public class OperationController {

    @Autowired
    OperationService operationService;

    @CrossOrigin
    @GetMapping(value = "/")
    public List<Operation> getOperations(){
        List<Operation> allOperations = operationService.getAllOperations();
        if (allOperations != null && !allOperations.isEmpty()) {
            return allOperations;
        }
        return Collections.emptyList();
    }

    @CrossOrigin
    @GetMapping(value = "/operations")
    public List<String> getOperationsInStringForm(){
        log.info("Getting all operations...");
        List<Operation> allOperations = operationService.getAllOperations();
        if (allOperations != null && !allOperations.isEmpty()) {
            return allOperations
                    .stream()
                    .sorted(Comparator.comparing(Operation::getTimestamp).reversed())
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @PostMapping(value = "/create")
    public Operation createOperation(@RequestBody @Valid Operation createOperationRequest){
        log.info("Saving new operation to storage...");
        return operationService.createOperation(createOperationRequest);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<HttpStatus> deleteOperation(){
        log.info("Deleting first operation from storage...");
        List<Operation> allOperations = operationService.getAllOperations();
        if (allOperations != null && !allOperations.isEmpty()) {
            List<Operation> sortedOperations = allOperations
                        .stream()
                        .sorted(Comparator.comparing(Operation::getTimestamp))
                        .toList();
                operationService.deleteOperation(sortedOperations.get(0)); //deletes the first operation
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/healthz")
    public ResponseEntity<HttpStatus> healthz(){
        log.info("Health check...");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
