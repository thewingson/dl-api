package kz.almat.dlapi.rest;

import kz.almat.dlapi.dto.SubjectClassDTO;
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

    //TODO: Cover responses by ResponseEntity or other response object.    @GetMapping
    public List<SubjectClassDTO> getAll() {
        return subjectClassRepository.findAllConvertedToDTO();
    }

    @GetMapping("{id}")
    public Optional<SubjectClassDTO> getOne(@PathVariable("id") Long id) {
        return subjectClassRepository.findByIdConvertedToDTO(id);
    }

    @PostMapping
    public void add(@RequestBody SubjectClassPOJO subjectClassPOJO) {
        subjectClassService.create(subjectClassPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<SubjectClassPOJO> subjectClassPOJOS) {
        subjectClassService.createAll(subjectClassPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                                             @RequestBody SubjectClassPOJO subjectClassPOJO) {
        subjectClassService.update(id, subjectClassPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        subjectClassRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        subjectClassRepository.deleteAll();
    }

}
