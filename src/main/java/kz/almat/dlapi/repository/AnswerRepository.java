package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Repository for {@link Answer}
 */

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
