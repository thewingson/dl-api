package kz.almat.dlapi.repository;

import kz.almat.dlapi.dto.SubjectClassDTO;
import kz.almat.dlapi.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link SubjectClass}
 */

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long> {

    @Query("select new kz.almat.dlapi.dto.SubjectClassDTO(" +
            "sc.id, " +
            "sc.subject.id, " +
            "sc.subject.name, " +
            "sc.group.id, " +
            "sc.group.grade, " +
            "sc.group.listNumber, " +
            "sc.teacher.id, " +
            "sc.teacher.firstName, " +
            "sc.teacher.lastName, " +
            "sc.teacher.middleName " +
            ") " +
            " from SubjectClass sc where sc.id = :id")
    Optional<SubjectClassDTO> findByIdConvertedToDTO(@Param("id") Long id);

    @Query("select new kz.almat.dlapi.dto.SubjectClassDTO(" +
            "sc.id, " +
            "sc.subject.id, " +
            "sc.subject.name, " +
            "sc.group.id, " +
            "sc.group.grade, " +
            "sc.group.listNumber, " +
            "sc.teacher.id, " +
            "sc.teacher.firstName, " +
            "sc.teacher.lastName, " +
            "sc.teacher.middleName " +
            ") " +
            " from SubjectClass sc")
    List<SubjectClassDTO> findAllConvertedToDTO();

    @Modifying
    @Query(value ="insert into almat.subject_class (id, group_id, subject_id, teacher_id) values(subject_class_id_seq.nextval, :subjectId, :groupId, :teacherId)",
            nativeQuery = true)
    void saveByIDs(@Param("subjectId") Long subjectId,
                   @Param("groupId") Long groupId,
                   @Param("teacherId") Long teacherId);

    @Modifying
    @Query(value = "update almat.subject_class sc " +
            "set sc.group_id = :groupId, " +
            "sc.subject_id = :subjectId, " +
            "sc.teacher_id = :teacherId " +
            "where sc.id = :id",
            nativeQuery = true)
    void updateById(@Param("subjectId") Long subjectId,
                   @Param("groupId") Long groupId,
                   @Param("teacherId") Long teacherId,
                   @Param("id") Long id);

}
