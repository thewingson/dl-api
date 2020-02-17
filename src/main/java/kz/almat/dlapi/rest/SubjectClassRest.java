package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Rest for {@link SubjectClass}
 */

@RestController
@RequestMapping("/subject-class")
public class SubjectClassRest {

    private final SubjectClassRepository subjectClassRepository;
    private final SubjectClassService subjectClassService;

    @Autowired
    public SubjectClassRest(SubjectClassRepository subjectClassRepository, SubjectClassService subjectClassService) {
        this.subjectClassRepository = subjectClassRepository;
        this.subjectClassService = subjectClassService;
    }

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<SubjectClass> getAll() {
        return subjectClassRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<SubjectClass> getOne(@PathVariable("id") Long id) {
        return subjectClassRepository.findById(id);
    }

    @PostMapping
    public SubjectClass add(@RequestBody SubjectClassPOJO subjectClassPOJO) {
        return subjectClassService.create(subjectClassPOJO);
    }

    @PostMapping("/all")
    public List<SubjectClass> addAll(@RequestBody List<SubjectClassPOJO> subjectClassPOJOS) {
        return subjectClassService.createAll(subjectClassPOJOS);
    }

    @PutMapping
    public SubjectClass edit(@RequestBody SubjectClassPOJO subjectClassPOJO) {
        return subjectClassService.update(subjectClassPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        subjectClassService.delete(id);
    }

    //TODO: Move it to service layer. Check if CrudRepository.deleteAll() method is transactional or not.
    @DeleteMapping("/all")
    public void deleteAll() {
        subjectClassRepository.deleteAll();
    }

}
