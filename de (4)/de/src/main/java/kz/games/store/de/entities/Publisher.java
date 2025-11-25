package kz.games.store.de.entities;

import lombok.*;
import jakarta.persistence.*;

@Setter
@Getter
@Table(name="publishers")
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String owner;
}
