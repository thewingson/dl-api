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

    @GetMapping
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Answer> getOne(@PathVariable("id") Long id) {
        return answerRepository.findById(id);
    }

    @PostMapping
    public void add(@RequestBody AnswerPOJO answerPOJO) {
        answerService.create(answerPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<AnswerPOJO> answerPOJOS) {
        answerService.createAll(answerPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                     @RequestBody AnswerPOJO answerPOJO) {
        answerService.update(id, answerPOJO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        answerRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        answerRepository.deleteAll();
    }

}
