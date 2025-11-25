package kz.games.store.de.dto;
import lombok.*;

import java.util.List;

@Data
@Builder
public class GameDto {
    private Long id;
    private String gameTitle;
    private String gameDescription;
    private int price;
    private String release;
    private String developer;
    private int ageRating;
    private Long publisherId;
    private List<Long> genreIds;
}
