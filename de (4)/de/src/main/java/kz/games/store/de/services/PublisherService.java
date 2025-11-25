package kz.games.store.de.services;

import kz.games.store.de.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> getPublishers();
    PublisherDto getPublisher(Long id);
    PublisherDto addPublisher(PublisherDto publisherDto);
    PublisherDto updatePublisher(Long id, PublisherDto publisherDto);
    boolean deletePublisher(Long id);
}
