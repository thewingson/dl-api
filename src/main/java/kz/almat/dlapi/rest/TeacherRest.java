package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Teacher;
import kz.almat.dlapi.pojo.TeacherPOJO;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Rest for {@link Teacher}
 */

@RestController
@RequestMapping("/teacher")
public class TeacherRest {

    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @Autowired
    public TeacherRest(TeacherRepository teacherRepository, TeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Teacher> getOne(@PathVariable("id") Long id) {
        return teacherRepository.findById(id);
    }

    @PostMapping
    public Teacher add(@RequestBody TeacherPOJO teacherPOJO) {
        return teacherService.create(teacherPOJO);
    }

    @PostMapping("/all")
    public List<Teacher> addAll(@RequestBody List<TeacherPOJO> teacherPOJOS) {
        return teacherService.createAll(teacherPOJOS);
    }

    @PutMapping("{id}")
    public Teacher edit(@PathVariable("id") Long id,
                        @RequestBody TeacherPOJO teacherPOJO) {
        return teacherService.update(id, teacherPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        teacherService.delete(id);
    }

    //TODO: Move it to service layer. Check if CrudRepository.deleteAll() method is transactional or not.
    @DeleteMapping("/all")
    public void deleteAll() {
        teacherRepository.deleteAll();
    }

}
