package kz.almat.dlapi.rest;

import kz.almat.dlapi.dto.StudentDTO;
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

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentRepository.findAllConvertedToDTO();
    }

    @GetMapping("{id}")
    public Optional<StudentDTO> getOne(@PathVariable("id") Long id) {
        return studentRepository.findByIdConvertedToDTO(id);
    }

    @PostMapping
    public void add(@RequestBody StudentPOJO studentPOJO) {
        studentService.create(studentPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<StudentPOJO> studentPOJOS) {
        studentService.createAll(studentPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                        @RequestBody StudentPOJO studentPOJO) {
        studentService.update(id, studentPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        studentRepository.deleteAll();
    }

}
