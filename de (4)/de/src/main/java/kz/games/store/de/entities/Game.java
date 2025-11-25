package kz.games.store.de.entities;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int price;
    private String release;
    private String developer;
    private int ageRating;
    @ManyToOne(fetch=FetchType.LAZY)
    private Publisher publisher;
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Genre> genres;
}
