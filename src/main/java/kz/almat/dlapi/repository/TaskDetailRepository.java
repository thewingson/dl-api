package kz.almat.dlapi.repository;

import kz.almat.dlapi.model.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Repository for {@link TaskDetail}
 */

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {

    TaskDetail findByTaskId(Long id);

}
