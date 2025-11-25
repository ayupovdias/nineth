package kz.games.store.de.services;

import kz.games.store.de.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getGenres();
    GenreDto getGenre(Long id);
    GenreDto addGenre(GenreDto genreDto);
    GenreDto updateGenre(Long id, GenreDto genreDto);
    boolean deleteGenre(Long id);
}
