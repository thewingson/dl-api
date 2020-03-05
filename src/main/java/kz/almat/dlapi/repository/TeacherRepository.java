package kz.almat.dlapi.repository;

import kz.almat.dlapi.dto.StudentDTO;
import kz.almat.dlapi.dto.TeacherDTO;
import kz.almat.dlapi.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Repository for {@link Teacher}
 */

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select new kz.almat.dlapi.dto.TeacherDTO(" +
            "t.id, " +
            "t.firstName, " +
            "t.lastName, " +
            "t.middleName, " +
            "t.department.id, " +
            "t.department.name, " +
            "t.department.code " +
            ") " +
            " from Teacher t where t.id = :id")
    Optional<TeacherDTO> findByIdConvertedToDTO(@Param("id") Long id);

    @Query("select new kz.almat.dlapi.dto.TeacherDTO(" +
            "t.id, " +
            "t.firstName, " +
            "t.lastName, " +
            "t.middleName, " +
            "t.department.id, " +
            "t.department.name, " +
            "t.department.code " +
            ") " +
            " from Teacher t")
    List<TeacherDTO> findAllConvertedToDTO();

}
