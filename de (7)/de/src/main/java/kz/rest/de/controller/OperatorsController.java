package kz.rest.de.controller;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.Entity.Operators;
import kz.rest.de.service.OperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/api/operators")
@RequiredArgsConstructor
@RestController
public class OperatorsController {
    private final OperatorsService operatorsService;

    @GetMapping
    public ResponseEntity<?> getOperators(){
        List<Operators> operators=operatorsService.getOperators();
        if(operators.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(operators);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOperator(@PathVariable("id") Long id){
        Operators operator=operatorsService.getOperator(id);
        if(operator==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(operator);
    }
    @PostMapping
    public ResponseEntity<?> addOperator(@RequestBody Operators operator){
        Operators addedOperator=operatorsService.addOperator(operator);
        return new ResponseEntity<>(addedOperator, HttpStatus.CREATED);
    }
    @PutMapping(value="/{id}/assign/{requestId}")
    public ResponseEntity<?> assignOperatorToApplicationRequest(@PathVariable("id") Long id, @PathVariable("requestId") Long requestId){
        ApplicationRequest applicationRequest = operatorsService.assignOperatorToApplicationRequest(id, requestId);
        if(applicationRequest==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(applicationRequest);
    }
    @PutMapping(value="/{id}/unassign/{requestId}")
    public ResponseEntity<?> unassignOperatorToApplicationRequest(@PathVariable("id") Long id, @PathVariable("requestId") Long requestId){
        ApplicationRequest applicationRequest=operatorsService.unassignOperatorToApplicationRequest(id, requestId);
        if(applicationRequest==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(applicationRequest);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteOperator(@PathVariable("id") Long id){
        boolean result=operatorsService.deleteOperator(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
