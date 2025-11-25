package kz.games.store.de.services.impl;

import kz.games.store.de.dto.GameDto;
import kz.games.store.de.dto.GenreDto;
import kz.games.store.de.dto.PublisherDto;
import kz.games.store.de.entities.Game;
import kz.games.store.de.entities.Genre;
import kz.games.store.de.entities.Publisher;
import kz.games.store.de.mapper.GameMapper;
import kz.games.store.de.mapper.PublisherMapper;
import kz.games.store.de.repositories.GameRepository;
import kz.games.store.de.services.GameService;
import kz.games.store.de.services.GenreService;
import kz.games.store.de.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final PublisherService publisherService;
    private final GenreService genreService;
    @Override
    public List<GameDto> getGames(){
        List<Game> games = gameRepository.findAll();
        List<GameDto> gamesDto=gameMapper.toDtoList(games);
        return gamesDto;
    }
    @Override
    public GameDto getGame(Long id){
        Game game=gameRepository.findById(id).orElse(null);
        if(game==null){
            return null;
        }
        GameDto gameDto=gameMapper.toDto(game);
        return gameDto;
    }
    @Override
    public GameDto addGame(GameDto gameDto){
        if(gameDto.getPrice()<0){
            gameDto.setPrice(0);
        }
        if(gameDto.getAgeRating()<0){
            gameDto.setAgeRating(0);
        }
        else if(gameDto.getAgeRating()>99){
            gameDto.setAgeRating(99);
        }
        if(gameDto.getPublisherId()!=0){
            PublisherDto publisherDto=publisherService.getPublisher(gameDto.getPublisherId());
            if(publisherDto==null){
                 gameDto.setPublisherId(0L);
            }
        }
        if(!gameDto.getGenreIds().isEmpty()){
            List<Long> genres=new ArrayList<>();
            for(Long i :gameDto.getGenreIds()){
                 GenreDto genreDto=genreService.getGenre(i);
                 if(genreDto!=null){
                     genres.add(genreDto.getId());
                 }
            }
            gameDto.setGenreIds(genres);
        }
        Game game=gameMapper.toEntity(gameDto);
        Game addedGame=gameRepository.save(game);
        GameDto dto=gameMapper.toDto(addedGame);
        return dto;
    }
    @Override
    public GameDto updateGame(Long id, GameDto gameDto){
        GameDto dto=getGame(id);
        if(dto!=null){
            if(gameDto.getGameTitle()!=null){
                dto.setGameTitle(gameDto.getGameTitle());
            }
            if(gameDto.getGameDescription()!=null){
                dto.setGameDescription(gameDto.getGameDescription());
            }
            if(gameDto.getPrice()<=0){
                dto.setPrice(0);
            }
            else{
                dto.setPrice(gameDto.getPrice());
            }
            if(gameDto.getRelease()!=null){
                dto.setRelease(gameDto.getRelease());
            }
            if(gameDto.getDeveloper()!=null){
                dto.setDeveloper(gameDto.getDeveloper());
            }
            if(gameDto.getAgeRating()<=0){
                dto.setAgeRating(0);
            }
            else if(gameDto.getAgeRating()>=99){
                dto.setAgeRating(99);
            }
            else{
                dto.setAgeRating(gameDto.getAgeRating());
            }
            if(dto.getPublisherId()>0){
                PublisherDto publisherDto=publisherService.getPublisher(dto.getPublisherId());
                if(publisherDto!=null){
                    dto.setPublisherId(publisherDto.getId());
                }
                else{
                    dto.setPublisherId(0L);
                }
            }
            if(!gameDto.getGenreIds().isEmpty()){
                List<Long> genres=new ArrayList<>();
                for(Long i :gameDto.getGenreIds()){
                    GenreDto genreDto=genreService.getGenre(i);
                    if(genreDto!=null){
                        genres.add(genreDto.getId());
                    }
                }
                dto.setGenreIds(genres);
            }
            Game game=gameMapper.toEntity(dto);
            Game updatedGame=gameRepository.save(game);
            GameDto updatedGameDto=gameMapper.toDto(updatedGame);
            return updatedGameDto;
        }
        return null;
    }
    @Override
    public boolean deleteGame(Long id){
        GameDto dto=getGame(id);
        if(dto!=null){
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
