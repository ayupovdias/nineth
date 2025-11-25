package kz.games.store.de.services.impl;

import kz.games.store.de.dto.GenreDto;
import kz.games.store.de.entities.Genre;
import kz.games.store.de.mapper.GenreMapper;
import kz.games.store.de.repositories.GenreRepository;
import kz.games.store.de.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    @Override
    public List<GenreDto> getGenres(){
        List<Genre> genres=genreRepository.findAll();
        List<GenreDto> genresDto=genreMapper.toListDto(genres);
        return genresDto;
    }
    @Override
    public GenreDto getGenre(Long id){
        Genre genre=genreRepository.findById(id).orElse(null);
        if(genre!=null){
            GenreDto genreDto=genreMapper.toDto(genre);
            return genreDto;
        }
        return null;
    }
    @Override
    public GenreDto addGenre(GenreDto genreDto){
        Genre genre=genreMapper.toEntity(genreDto);
        Genre addedGenre=genreRepository.save(genre);
        GenreDto dto=genreMapper.toDto(addedGenre);
        return dto;
    }
    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto){
        GenreDto dto=getGenre(id);
        if(dto!=null){
            if(genreDto.getGenreName()!=null){
                dto.setGenreName(genreDto.getGenreName());
            }
            Genre genre=genreMapper.toEntity(dto);
            Genre updatedGenre=genreRepository.save(genre);
            GenreDto updatedDto=genreMapper.toDto(updatedGenre);
            return updatedDto;
        }
        return null;
    }
    @Override
    public boolean deleteGenre(Long id){
        GenreDto dto=getGenre(id);
        if(dto!=null){
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
