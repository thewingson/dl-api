package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat on 15.02.2020
 *
 * Repository for {@link Group}
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
