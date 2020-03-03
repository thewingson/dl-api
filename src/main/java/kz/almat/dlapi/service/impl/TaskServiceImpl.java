package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.model.TaskDetail;
import kz.almat.dlapi.pojo.TaskPOJO;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.TaskRepository;
import kz.almat.dlapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Implementation of service {@link TaskService}
 */

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final SubjectClassRepository subjectClassRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           SubjectClassRepository subjectClassRepository) {
        this.taskRepository = taskRepository;
        this.subjectClassRepository = subjectClassRepository;
    }

    @Transactional
    @Override
    public Task create(TaskPOJO taskPOJO) {
        List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(taskPOJO.getSubjectClasses());
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTopic(taskPOJO.getTopic());
        taskDetail.setDescription(taskPOJO.getDescription());
        taskDetail.setDeadline(taskPOJO.getDeadline());

        Task task = new Task();
        task.setValue(taskPOJO.getValue());
        task.setSubjectClasses(new HashSet<>(subjectClasses));
        task.setTaskDetail(taskDetail);
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public List<Task> createAll(List<TaskPOJO> taskPOJOS) {
        List<Task> tasks = new ArrayList<>();
        for (TaskPOJO t : taskPOJOS) {
            List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(t.getSubjectClasses());
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setTopic(t.getTopic());
            taskDetail.setDescription(t.getDescription());
            taskDetail.setDeadline(t.getDeadline());

            Task task = new Task();
            task.setValue(t.getValue());
            task.setSubjectClasses(new HashSet<>(subjectClasses));
            task.setTaskDetail(taskDetail);
            tasks.add(task);
        }
        return taskRepository.saveAll(tasks);
    }

    @Transactional
    @Override
    public Task update(Long id, TaskPOJO taskPOJO) {
        List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(taskPOJO.getSubjectClasses());
        Task task = new Task();
        task.setId(id);
        task.setValue(taskPOJO.getValue());
        task.setSubjectClasses(new HashSet<>(subjectClasses));
        task.setTaskDetail(new TaskDetail(taskPOJO.getTopic(), taskPOJO.getDescription(), taskPOJO.getDeadline()));
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
