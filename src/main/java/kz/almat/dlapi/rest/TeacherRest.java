package kz.almat.dlapi.rest;

import kz.almat.dlapi.dto.TeacherDTO;
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

    @GetMapping
    public List<TeacherDTO> getAll() {
        return teacherRepository.findAllConvertedToDTO();
    }

    @GetMapping("{id}")
    public Optional<TeacherDTO> getOne(@PathVariable("id") Long id) {
        return teacherRepository.findByIdConvertedToDTO(id);
    }

    @PostMapping
    public void add(@RequestBody TeacherPOJO teacherPOJO) {
        teacherService.create(teacherPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<TeacherPOJO> teacherPOJOS) {
        teacherService.createAll(teacherPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                        @RequestBody TeacherPOJO teacherPOJO) {
        teacherService.update(id, teacherPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        teacherRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        teacherRepository.deleteAll();
    }

}
