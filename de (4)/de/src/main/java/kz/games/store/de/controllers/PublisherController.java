package kz.games.store.de.controllers;

import kz.games.store.de.dto.PublisherDto;
import kz.games.store.de.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value="/publishers")
@RestController
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<?> getPublishers(){
        List<PublisherDto> publishers=publisherService.getPublishers();
        if(publishers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(publishers);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getPubliser(@PathVariable("id") Long id){
        PublisherDto dto=publisherService.getPublisher(id);
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody PublisherDto publisherDto){
        PublisherDto dto=publisherService.addPublisher(publisherDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable("id") Long id,
                                             @RequestBody PublisherDto publisherDto){
        PublisherDto dto=publisherService.updatePublisher(id, publisherDto);
        if(dto==null){
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable("id") Long id){
        boolean result =publisherService.deletePublisher(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
