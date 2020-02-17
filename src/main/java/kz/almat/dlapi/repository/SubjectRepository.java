package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link Subject}
 */

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
