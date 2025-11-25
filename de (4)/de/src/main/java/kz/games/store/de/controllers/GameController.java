package kz.games.store.de.controllers;

import kz.games.store.de.dto.GameDto;
import kz.games.store.de.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<?> getGames(){
        List<GameDto> games=gameService.getGames();
        if(games.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(games);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getGame(@PathVariable("id") Long id){
        GameDto dto=gameService.getGame(id);
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> addGame(@RequestBody GameDto gameDto){
        GameDto dto=gameService.addGame(gameDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateGame(@PathVariable("id") Long id, @RequestBody GameDto gameDto){
        GameDto dto=gameService.updateGame(id, gameDto);
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable("id") Long id){
        boolean result=gameService.deleteGame(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
