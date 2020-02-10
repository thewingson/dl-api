package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat on 09.02.2020
 *
 * Repository for {@link Faculty}
 */

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
