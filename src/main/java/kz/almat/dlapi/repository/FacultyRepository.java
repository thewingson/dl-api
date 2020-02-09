package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Almat on 09.02.2020
 *
 * Repository for {@link Faculty}
 */

@org.springframework.stereotype.Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
