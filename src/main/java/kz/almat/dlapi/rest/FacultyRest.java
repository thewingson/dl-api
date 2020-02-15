package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 09.02.2020
 * <p>
 * Rest for {@link Faculty}
 */
@RestController
@RequestMapping("/faculty")
public class FacultyRest {

    private final FacultyRepository facultyRepository;
    private final FacultyService facultyService;

    @Autowired
    public FacultyRest(FacultyRepository facultyRepository, FacultyService facultyService) {
        this.facultyRepository = facultyRepository;
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Faculty> getOne(@PathVariable("id") Long id) {
        return facultyRepository.findById(id);
    }

    @PostMapping
    public Faculty add(@RequestBody FacultyPOJO facultyPOJO) {
        return facultyService.create(facultyPOJO);
    }

    @PostMapping("/all")
    public List<Faculty> addAll(@RequestBody List<FacultyPOJO> facultyPOJOS) {
        return facultyService.createAll(facultyPOJOS);
    }

    @PutMapping
    public Faculty edit(@RequestBody FacultyPOJO facultyPOJO) {
        return facultyService.update(facultyPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        facultyService.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        facultyRepository.deleteAll();
    }

}
