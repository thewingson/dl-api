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
 *
 * Rest for {@link Faculty}
 */

//TODO: Cover responses by ResponseEntity or other response object.
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
    public void add(@RequestBody FacultyPOJO facultyPOJO) {
        facultyService.create(facultyPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<FacultyPOJO> facultyPOJOS) {
        facultyService.createAll(facultyPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                        @RequestBody FacultyPOJO facultyPOJO) {
        facultyService.update(id, facultyPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        facultyRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        facultyRepository.deleteAll();
    }


}
