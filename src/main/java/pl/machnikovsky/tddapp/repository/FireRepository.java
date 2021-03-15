package pl.machnikovsky.tddapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.machnikovsky.tddapp.model.Fire;


@Repository
public interface FireRepository extends JpaRepository<Fire, Integer> {
}

