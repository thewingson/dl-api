package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link SubjectClass}
 */

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long> {

    @Modifying
    @Query(value = "insert into almat.subject_class (id, group_id, subject_id, teacher_id) values(subject_class_id_seq.nextval, :subjectId, :groupId, :teacherId)",
            nativeQuery = true)
    void saveByIDs(@Param("subjectId") Long subjectId, @Param("groupId") Long groupId, @Param("teacherId") Long teacherId);

}
