package kz.games.store.de.dto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
public class GenreDto {
    private Long id;
    private String genreName;
}
