package kz.rest.de.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="application_requests")
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String comment;
    private String phone;
    private boolean handled;
    @ManyToOne
    private Course course;
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Operators> operators;
}
