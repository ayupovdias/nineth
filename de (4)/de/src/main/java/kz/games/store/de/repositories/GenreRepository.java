package kz.games.store.de.repositories;

import kz.games.store.de.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
