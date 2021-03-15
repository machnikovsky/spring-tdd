package pl.machnikovsky.tddapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Firestation;

@Repository
public interface FirestationRepository extends JpaRepository<Firestation, Integer> {
}
