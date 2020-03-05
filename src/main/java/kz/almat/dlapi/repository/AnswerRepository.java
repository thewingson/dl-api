package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 * <p>
 * Repository for {@link Answer}
 */

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query(value = "insert into almat.answer (id, task_id, student_id, message) values(answer_id_seq.nextval, :taskId, :studentId, :message)",
            nativeQuery = true)
    void saveByIDs(@Param("taskId") Long taskId,
                   @Param("studentId") Long studentId,
                   @Param("message") String message);

    @Modifying
    @Query(value = "update almat.answer a " +
            "set a.task_id = :taskId, " +
            "a.student_id = :studentId, " +
            "a.message = :message " +
            "where a.id = :id",
            nativeQuery = true)
    void updateById(@Param("taskId") Long taskId,
                    @Param("studentId") Long studentId,
                    @Param("message") String message,
                    @Param("id") Long id);

}
