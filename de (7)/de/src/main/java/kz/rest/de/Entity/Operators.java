package kz.rest.de.Entity;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Operators {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String department;
}
