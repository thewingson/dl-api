package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.DepartmentPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 10.02.2020
 *
 * Rest for {@link Faculty}
 */

@RestController
@RequestMapping("/department")
public class DepartmentRest {

    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentRest(DepartmentRepository departmentRepository, DepartmentService departmentService) {
        this.departmentRepository = departmentRepository;
        this.departmentService = departmentService;
    }

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Department> getOne(@PathVariable("id") Long id) {
        return departmentRepository.findById(id);
    }

    @PostMapping
    public Department add(@RequestBody DepartmentPOJO departmentPOJO) {
        return departmentService.create(departmentPOJO);
    }

    @PostMapping("/all")
    public List<Department> addAll(@RequestBody List<DepartmentPOJO> departmentPOJOS) {
        return departmentService.createAll(departmentPOJOS);
    }

    @PutMapping
    public Department edit(@RequestBody DepartmentPOJO departmentPOJOS) {
        return departmentService.update(departmentPOJOS);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        departmentRepository.deleteAll();
    }

}
