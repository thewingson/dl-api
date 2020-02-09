package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Faculty;
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
@RestController
@RequestMapping("/faculty")
public class FacultyRest{

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Faculty> getAll(){
        return facultyRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Faculty> getOne(@PathVariable("id") Long id){
        return facultyRepository.findById(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty){
        return facultyService.create(faculty);
    }

    @PostMapping("/all")
    public List<Faculty> addAll(@RequestBody List<Faculty> faculties){
        return facultyService.createAll(faculties);
    }

    @PutMapping
    public Faculty edit(@RequestBody Faculty faculty){
        return facultyService.update(faculty);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        facultyService.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll(){
        facultyRepository.deleteAll();
    }

}
