package kz.games.store.de.repositories;

import kz.games.store.de.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
}
