package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.AnswerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Repository for {@link AnswerDetail}
 */

@Repository
public interface AnswerDetailRepository extends JpaRepository<AnswerDetail, Long> {
}
