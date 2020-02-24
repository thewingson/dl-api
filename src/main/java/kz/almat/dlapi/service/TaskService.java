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
    Task create(TaskPOJO taskPOJO);
    List<Task> createAll(List<TaskPOJO> taskPOJOS);
    Task update(TaskPOJO taskPOJO);
    void delete(Long id);
}
