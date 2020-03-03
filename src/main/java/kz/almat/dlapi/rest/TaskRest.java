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

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Task> getOne(@PathVariable("id") Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping
    public Task add(@RequestBody TaskPOJO taskPOJO) {
        return taskService.create(taskPOJO);
    }

    @PostMapping("/all")
    public List<Task> addAll(@RequestBody List<TaskPOJO> taskPOJOS) {
        return taskService.createAll(taskPOJOS);
    }

    @PutMapping("{id}")
    public Task edit(@PathVariable("id") Long id,
                     @RequestBody TaskPOJO taskPOJO) {
        return taskService.update(id, taskPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    //TODO: Move it to service layer. Check if CrudRepository.deleteAll() method is transactional or not.
    @DeleteMapping("/all")
    public void deleteAll() {
        taskRepository.deleteAll();
    }

}
