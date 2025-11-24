package kz.rest.de.controller;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/api/requests")
@RestController
@RequiredArgsConstructor
public class ApplicationRequestController {
    private final ApplicationRequestService applicationRequestService;

    @GetMapping
    public ResponseEntity<?> getApplicationRequests(){
        List<ApplicationRequest> applicationRequests=applicationRequestService.getApplicationRequests();
        if(applicationRequests.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(applicationRequests);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getApplicationRequest(@PathVariable("id") Long id){
        ApplicationRequest applicationRequest=applicationRequestService.getApplicationRequest(id);
        if(applicationRequest==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(applicationRequest);
    }
    @PostMapping
    public ResponseEntity<?> addApplicationRequest(@RequestBody ApplicationRequest applicationRequest){
        ApplicationRequest addedApplicationRequest=applicationRequestService.addApplicationRequest(applicationRequest);
        return new ResponseEntity<>(addedApplicationRequest, HttpStatus.CREATED);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateApplicationRequest(@PathVariable("id") Long id,
                                                      @RequestBody ApplicationRequest applicationRequest){
        ApplicationRequest updatedApplicationRequest=applicationRequestService.updateApplicationRequest(id, applicationRequest);
        if(updatedApplicationRequest==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedApplicationRequest);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteApplicationRequest(@PathVariable("id") Long id){
        boolean result=applicationRequestService.deleteApplicationRequest(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
