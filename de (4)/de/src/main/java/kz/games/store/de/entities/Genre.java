package kz.games.store.de.entities;

import lombok.*;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name="genres")
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
}
