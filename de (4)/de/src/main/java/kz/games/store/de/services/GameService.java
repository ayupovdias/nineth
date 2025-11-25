package kz.games.store.de.services;

import kz.games.store.de.dto.GameDto;

import java.util.List;

public interface GameService {
    List<GameDto> getGames();
    GameDto getGame(Long id);
    GameDto addGame(GameDto gameDto);
    GameDto updateGame(Long id, GameDto gameDto);
    boolean deleteGame(Long id);
}
