package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.pojo.TaskPOJO;
import kz.almat.dlapi.repository.TaskRepository;
import kz.almat.dlapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * Rest for {@link Task}
 */

@RestController
@RequestMapping("/task")
public class TaskRest {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Autowired
    public TaskRest(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Task> getOne(@PathVariable("id") Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping
    public void add(@RequestBody TaskPOJO taskPOJO) {
        taskService.create(taskPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<TaskPOJO> taskPOJOS) {
        taskService.createAll(taskPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                     @RequestBody TaskPOJO taskPOJO) {
        taskService.update(id, taskPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        taskRepository.deleteAll();
    }

}
