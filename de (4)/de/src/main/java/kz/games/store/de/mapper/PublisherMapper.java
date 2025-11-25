package kz.games.store.de.mapper;

import kz.games.store.de.dto.PublisherDto;
import kz.games.store.de.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface PublisherMapper {
    @Mapping(source="publisherName", target="name")
    @Mapping(source="publisherOwner", target="owner")
    Publisher toEntity(PublisherDto publisherDto);

    @Mapping(source="name", target="publisherName")
    @Mapping(source="owner", target="publisherOwner")
    PublisherDto toDto(Publisher publisher);

    List<PublisherDto> toListDto(List<Publisher> publishers);
}
