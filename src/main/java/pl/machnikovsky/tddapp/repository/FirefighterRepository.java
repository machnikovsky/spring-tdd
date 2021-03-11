package pl.machnikovsky.tddapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.machnikovsky.tddapp.model.Firefighter;

@Repository
public interface FirefighterRepository extends JpaRepository<Firefighter, Integer> {
}
