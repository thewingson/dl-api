package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 10.02.2020
 * <p>
 * Rest for {@link Faculty}
 */

@RestController
@RequestMapping("/department")
public class DepartmentRest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Department> getOne(@PathVariable("id") Long id) {
        return departmentRepository.findById(id);
    }

    @PostMapping
    public Department add(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @PostMapping("/all")
    public List<Department> addAll(@RequestBody List<Department> departments) {
        return departmentService.createAll(departments);
    }

    @PutMapping
    public Department edit(@RequestBody Department department) {
        return departmentService.update(department);
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
