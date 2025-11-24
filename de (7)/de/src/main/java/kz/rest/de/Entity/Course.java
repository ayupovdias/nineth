package kz.rest.de.Entity;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
}
