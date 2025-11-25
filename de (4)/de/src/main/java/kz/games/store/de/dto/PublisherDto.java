package kz.games.store.de.dto;
import lombok.*;
@Setter
@Getter
@Builder
public class PublisherDto {
    private Long id;
    private String publisherName;
    private String publisherOwner;
}
