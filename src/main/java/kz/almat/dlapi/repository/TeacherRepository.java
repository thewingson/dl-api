package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link Teacher}
 */

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
