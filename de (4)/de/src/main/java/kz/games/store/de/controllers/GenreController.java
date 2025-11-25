package kz.games.store.de.controllers;


import kz.games.store.de.dto.GenreDto;
import kz.games.store.de.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequestMapping(value="/genres")
@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getGenres(){
        List<GenreDto> genresDto=genreService.getGenres();
        if(genresDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(genresDto);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getGenre(@PathVariable("id") Long id){
        GenreDto dto=genreService.getGenre(id);
        if(dto!=null){
            return ResponseEntity.ok(dto);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addGenre(@RequestBody GenreDto genreDto){
        GenreDto dto=genreService.addGenre(genreDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable("id") Long id,
                                         @RequestBody GenreDto genreDto){
        GenreDto dto=genreService.updateGenre(id,genreDto);
        if(dto!=null){
            return ResponseEntity.ok(dto);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable("id") Long id){
        boolean result=genreService.deleteGenre(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
