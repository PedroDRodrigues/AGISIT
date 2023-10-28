package pt.tecnico.agi.storage.domain.repository;

import pt.tecnico.agi.storage.domain.model.Operation;
import org.springframework.data.repository.CrudRepository;


public interface OperationRepository extends CrudRepository<Operation, Long> {
}
