package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link Student}
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
