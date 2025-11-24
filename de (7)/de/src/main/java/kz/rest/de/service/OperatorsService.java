package kz.rest.de.service;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.Entity.Operators;

import java.util.List;

public interface OperatorsService {
    List<Operators> getOperators();
    Operators getOperator(Long id);
    Operators addOperator(Operators operator);
    ApplicationRequest assignOperatorToApplicationRequest(Long id, Long requestId);
    ApplicationRequest unassignOperatorToApplicationRequest(Long id, Long requestId);
    boolean deleteOperator(Long id);
}
