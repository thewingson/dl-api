package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat on 10.02.2020
 *
 * Repository for {@link Department}
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
