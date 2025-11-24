package kz.rest.de.repository;

import kz.rest.de.Entity.Operators;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface OperatorsRepository extends JpaRepository<Operators, Long>{

}
