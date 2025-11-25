package kz.games.store.de.services.impl;

import kz.games.store.de.dto.GameDto;
import kz.games.store.de.dto.PublisherDto;
import kz.games.store.de.entities.Game;
import kz.games.store.de.entities.Publisher;
import kz.games.store.de.mapper.GameMapper;
import kz.games.store.de.mapper.PublisherMapper;
import kz.games.store.de.repositories.GameRepository;
import kz.games.store.de.repositories.PublisherRepository;
import kz.games.store.de.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    @Override
    public List<PublisherDto> getPublishers(){
        List<Publisher> publishers=publisherRepository.findAll();
        List<PublisherDto> publishersDto=publisherMapper.toListDto(publishers);
        return publishersDto;
    }
    @Override
    public PublisherDto getPublisher(Long id){
        Publisher publisher=publisherRepository.findById(id).orElse(null);
        if(publisher==null){
            return null;
        }
        PublisherDto dto=publisherMapper.toDto(publisher);
        return dto;
    }
    @Override
    public PublisherDto addPublisher(PublisherDto publisherDto){
        Publisher publisher=publisherMapper.toEntity(publisherDto);
        Publisher addedPublisher=publisherRepository.save(publisher);
        PublisherDto dto=publisherMapper.toDto(addedPublisher);
        return dto;
    }
    @Override
    public PublisherDto updatePublisher(Long id, PublisherDto publisherDto){
        PublisherDto dto=getPublisher(id);
        if(dto!=null){
            if(publisherDto.getPublisherName()!=null){
                dto.setPublisherName(publisherDto.getPublisherName());
            }
            if(publisherDto.getPublisherOwner()!=null){
                dto.setPublisherOwner(publisherDto.getPublisherOwner());
            }
            Publisher publisher=publisherMapper.toEntity(dto);
            Publisher updatedPublisher=publisherRepository.save(publisher);
            PublisherDto updatedPublisherDto=publisherMapper.toDto(updatedPublisher);
            return updatedPublisherDto;
        }
        return null;
    }
    @Override
    public boolean deletePublisher(Long id){
        PublisherDto dto=getPublisher(id);
        List<Game> games=gameRepository.findAll();
        if(dto!=null) {
            for(int i=0;i<games.size();i++){
                if(games.get(i).getPublisher().getId()==id){
                    gameRepository.deleteById(games.get(i).getId());
                }
            }
            publisherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
