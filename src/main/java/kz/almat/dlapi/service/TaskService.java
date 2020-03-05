package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.pojo.TaskPOJO;

import java.util.List;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Service for {@link Task}
 */
public interface TaskService {
    void create(TaskPOJO taskPOJO);
    void createAll(List<TaskPOJO> taskPOJOS);
    void update(Long id, TaskPOJO taskPOJO);
}
