package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.pojo.SubjectPOJO;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Rest for {@link Subject}
 */

@RestController
@RequestMapping("/subject")
public class SubjectRest {

    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;

    @Autowired
    public SubjectRest(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Subject> getOne(@PathVariable("id") Long id) {
        return subjectRepository.findById(id);
    }

    @PostMapping
    public void add(@RequestBody SubjectPOJO subjectPOJO) {
        subjectService.create(subjectPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<SubjectPOJO> subjectPOJOS) {
        subjectService.createAll(subjectPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                        @RequestBody SubjectPOJO subjectPOJO) {
        subjectService.update(id, subjectPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        subjectRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        subjectRepository.deleteAll();
    }

}
