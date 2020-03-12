package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.pojo.AnswerPOJO;
import kz.almat.dlapi.repository.AnswerRepository;
import kz.almat.dlapi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * Implementation of service {@link AnswerService}
 */

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void create(AnswerPOJO answerPOJO) {
        answerRepository.saveByIDs(answerPOJO.getTaskId(), answerPOJO.getStudentId(), answerPOJO.getMessage());
    }

    @Override
    public void createAll(List<AnswerPOJO> answerPOJOS) {
        for (AnswerPOJO a : answerPOJOS) {
            answerRepository.saveByIDs(a.getTaskId(), a.getStudentId(), a.getMessage());
        }
    }

    @Override
    public void update(Long id, AnswerPOJO answerPOJO) {
        answerRepository.updateById(answerPOJO.getTaskId(), answerPOJO.getStudentId(), answerPOJO.getMessage(), id);
    }

}
