package kz.almat.dlapi.repository;

import kz.almat.dlapi.dto.GroupDTO;
import kz.almat.dlapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 15.02.2020
 *
 * Repository for {@link Group}
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select new kz.almat.dlapi.dto.GroupDTO(" +
            "g.id, " +
            "g.grade, " +
            "g.listNumber, " +
            "g.department.id, " +
            "g.department.name " +
            ") " +
            " from Group g where g.id = :id")
    Optional<GroupDTO> findByIdConvertedToDTO(@Param("id") Long id);

    @Query("select new kz.almat.dlapi.dto.GroupDTO(" +
            "g.id, " +
            "g.grade, " +
            "g.listNumber, " +
            "g.department.id, " +
            "g.department.name " +
            ") " +
            " from Group g")
    List<GroupDTO> findAllConvertedToDTO();

}
