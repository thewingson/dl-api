package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.pojo.StudentPOJO;
import kz.almat.dlapi.repository.StudentRepository;
import kz.almat.dlapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Rest for {@link Student}
 */

@RestController
@RequestMapping("/student")
public class StudentRest {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public StudentRest(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Student> getOne(@PathVariable("id") Long id) {
        return studentRepository.findById(id);
    }

    @PostMapping
    public Student add(@RequestBody StudentPOJO studentPOJO) {
        return studentService.create(studentPOJO);
    }

    @PostMapping("/all")
    public List<Student> addAll(@RequestBody List<StudentPOJO> studentPOJOS) {
        return studentService.createAll(studentPOJOS);
    }

    @PutMapping("{id}")
    public Student edit(@PathVariable("id") Long id,
                        @RequestBody StudentPOJO studentPOJO) {
        return studentService.update(id, studentPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    //TODO: Move it to service layer. Check if CrudRepository.deleteAll() method is transactional or not.
    @DeleteMapping("/all")
    public void deleteAll() {
        studentRepository.deleteAll();
    }

}
