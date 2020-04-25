package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.pojo.AnswerPOJO;
import kz.almat.dlapi.repository.AnswerRepository;
import kz.almat.dlapi.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author almat_rakhmetolla on 12.03.2020
 * <p>
 * Unit test for {@link AnswerService}
 */

class AnswerServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private AnswerService answerService;

    @MockBean
    private AnswerRepository answerRepository;

    @Test
    void create_ok() throws Exception {
        AnswerPOJO answerPOJO = new AnswerPOJO(null, 1L, 1L, "Test1");

        answerService.create(answerPOJO);
        verify(answerRepository).saveByIDs(1L, 1L, "Test1");
    }

    @Test
    void createAll_ok() throws Exception {
        List<AnswerPOJO> answerPOJOS = new ArrayList<>();
        answerPOJOS.add(new AnswerPOJO(null, 1L, 1L, "Test1"));
        answerPOJOS.add(new AnswerPOJO(null, 2L, 2L, "Test2"));

        answerService.createAll(answerPOJOS);
        verify(answerRepository).saveByIDs(1L, 1L, "Test1");
        verify(answerRepository).saveByIDs(2L, 2L, "Test2");
    }

    @Test
    void update_ok() throws Exception {
        AnswerPOJO answerPOJO = new AnswerPOJO(null, 1L, 1L, "Test1");

        answerService.update(1L, answerPOJO);
        verify(answerRepository).updateById(1L, 1L, "Test1", 1L);
    }
}