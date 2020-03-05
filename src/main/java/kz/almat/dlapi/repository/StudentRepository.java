package kz.almat.dlapi.repository;

import kz.almat.dlapi.dto.StudentDTO;
import kz.almat.dlapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link Student}
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new kz.almat.dlapi.dto.StudentDTO(" +
            "s.id, " +
            "s.firstName, " +
            "s.lastName, " +
            "s.middleName, " +
            "s.group.id, " +
            "s.group.grade, " +
            "s.group.listNumber " +
            ") " +
            " from Student s where s.id = :id")
    Optional<StudentDTO> findByIdConvertedToDTO(@Param("id") Long id);

    @Query("select new kz.almat.dlapi.dto.StudentDTO(" +
            "s.id, " +
            "s.firstName, " +
            "s.lastName, " +
            "s.middleName, " +
            "s.group.id, " +
            "s.group.grade, " +
            "s.group.listNumber " +
            ") " +
            " from Student s")
    List<StudentDTO> findAllConvertedToDTO();

}
