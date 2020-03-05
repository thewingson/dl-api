package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Answer;
import kz.almat.dlapi.pojo.AnswerPOJO;

import java.util.List;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * Service for {@link Answer}
 */
public interface AnswerService {
    void create(AnswerPOJO answerPOJO);
    void createAll(List<AnswerPOJO> answerPOJOS);
    void update(Long id, AnswerPOJO answerPOJO);
}
