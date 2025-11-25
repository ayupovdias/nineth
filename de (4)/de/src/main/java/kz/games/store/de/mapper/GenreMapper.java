package kz.games.store.de.mapper;

import kz.games.store.de.dto.GenreDto;
import kz.games.store.de.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface GenreMapper {
    @Mapping(source="name", target="genreName")
    GenreDto toDto(Genre genre);

    @Mapping(source="genreName", target="name")
    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toListDto(List<Genre> genres);
    default Long mapGenreToId(Genre genre) {
        return genre != null ? genre.getId() : null;
    }

    default Genre mapIdToGenre(Long id) {
        if (id == null) return null;
        Genre g = new Genre();
        g.setId(id);
        return g;
    }
}
