package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.model.TaskDetail;
import kz.almat.dlapi.pojo.TaskPOJO;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.TaskDetailRepository;
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
    private final TaskDetailRepository taskDetailRepository;
    private final SubjectClassRepository subjectClassRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskDetailRepository taskDetailRepository,
                           SubjectClassRepository subjectClassRepository) {
        this.taskRepository = taskRepository;
        this.taskDetailRepository = taskDetailRepository;
        this.subjectClassRepository = subjectClassRepository;
    }

    @Transactional
    @Override
    public Task create(TaskPOJO taskPOJO) {
        List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(taskPOJO.getSubjectClasses());
        Task task = new Task();
        task.setValue(taskPOJO.getValue());
        task.setSubjectClasses(new HashSet<>(subjectClasses));
        Task savedTask = taskRepository.save(task);

        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTopic(taskPOJO.getTopic());
        taskDetail.setDescription(taskPOJO.getDescription());
        taskDetail.setDeadline(taskPOJO.getDeadline());
        taskDetail.setTask(savedTask);
        taskDetailRepository.save(taskDetail);

        return task;
    }

    @Transactional
    @Override
    public List<Task> createAll(List<TaskPOJO> taskPOJOS) {
        List<Task> tasks = new ArrayList<>();
        for (TaskPOJO t : taskPOJOS) {
            List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(t.getSubjectClasses());
            Task task = new Task();
            task.setValue(t.getValue());
            task.setSubjectClasses(new HashSet<>(subjectClasses));
            Task savedTask = taskRepository.save(task);

            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setTopic(t.getTopic());
            taskDetail.setDescription(t.getDescription());
            taskDetail.setDeadline(t.getDeadline());
            taskDetail.setTask(savedTask);
            taskDetailRepository.save(taskDetail);

            tasks.add(task);
        }

        return tasks;
    }

    @Transactional
    @Override
    public Task update(TaskPOJO taskPOJO) {
        List<SubjectClass> subjectClasses = subjectClassRepository.findAllById(taskPOJO.getSubjectClasses());
        Task task = new Task();
        task.setId(taskPOJO.getId());
        task.setValue(taskPOJO.getValue());
        task.setSubjectClasses(new HashSet<>(subjectClasses));
        Task savedTask = taskRepository.save(task);

        TaskDetail taskDetail = taskDetailRepository.findByTaskId(taskPOJO.getId());
        taskDetail.setTopic(taskPOJO.getTopic());
        taskDetail.setDescription(taskPOJO.getDescription());
        taskDetail.setDeadline(taskPOJO.getDeadline());
        taskDetail.setTask(savedTask);
        taskDetailRepository.save(taskDetail);

        return task;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
