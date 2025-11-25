package kz.games.store.de.mapper;

import kz.games.store.de.entities.Game;
import kz.games.store.de.dto.GameDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring", uses=GenreMapper.class)
public interface GameMapper {
    @Mapping(source="title", target="gameTitle")
    @Mapping(source="description", target="gameDescription")
    @Mapping(source="publisher.id", target="publisherId")
    @Mapping(source="genres", target="genreIds")
    GameDto toDto(Game game);

    @Mapping(source="gameTitle", target="title")
    @Mapping(source="gameDescription", target="description")
    @Mapping(source="publisherId", target="publisher.id")
    @Mapping(source="genreIds", target="genres")
    Game toEntity(GameDto gameDto);


    List<GameDto> toDtoList(List<Game> games);
}
