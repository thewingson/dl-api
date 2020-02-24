package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 *  Repository for {@link Task}
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
