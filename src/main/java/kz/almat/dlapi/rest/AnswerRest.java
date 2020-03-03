package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Answer;
import kz.almat.dlapi.pojo.AnswerPOJO;
import kz.almat.dlapi.repository.AnswerRepository;
import kz.almat.dlapi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * Rest for {@link Answer}
 */

@RestController
@RequestMapping("/answer")
public class AnswerRest {

    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    @Autowired
    public AnswerRest(AnswerRepository answerRepository, AnswerService answerService) {
        this.answerRepository = answerRepository;
        this.answerService = answerService;
    }

    //TODO: Cover responses by ResponseEntity or other response object.
    @GetMapping
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Answer> getOne(@PathVariable("id") Long id) {
        return answerRepository.findById(id);
    }

    @PostMapping
    public Answer add(@RequestBody AnswerPOJO answerPOJO) {
        return answerService.create(answerPOJO);
    }

    @PostMapping("/all")
    public List<Answer> addAll(@RequestBody List<AnswerPOJO> answerPOJOS) {
        return answerService.createAll(answerPOJOS);
    }

    @PutMapping("{id}")
    public Answer edit(@PathVariable("id") Long id,
                     @RequestBody AnswerPOJO answerPOJO) {
        return answerService.update(id, answerPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        answerService.delete(id);
    }

    //TODO: Move it to service layer. Check if CrudRepository.deleteAll() method is transactional or not.
    @DeleteMapping("/all")
    public void deleteAll() {
        answerRepository.deleteAll();
    }

}
