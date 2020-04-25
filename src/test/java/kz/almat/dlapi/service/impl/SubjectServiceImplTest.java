package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.pojo.SubjectPOJO;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author almat_rakhmetolla on 10.03.2020
 * <p>
 * Unit test for {@link SubjectService}
 */

class SubjectServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private SubjectService subjectService;

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    void create_ok() throws Exception {
        SubjectPOJO subjectPOJO = new SubjectPOJO(null, "Test1");
        Subject subject = new Subject(null, "Test1", new ArrayList<>());

        subjectService.create(subjectPOJO);
        verify(subjectRepository).save(subject);
    }

    @Test
    void createAll_ok() throws Exception {
        List<SubjectPOJO> subjectPOJOS = new ArrayList<>();
        subjectPOJOS.add(new SubjectPOJO(null, "Test1"));
        subjectPOJOS.add(new SubjectPOJO(null, "Test2"));
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(null, "Test1", new ArrayList<>()));
        subjects.add(new Subject(null, "Test2", new ArrayList<>()));

        subjectService.createAll(subjectPOJOS);
        verify(subjectRepository).saveAll(subjects);
    }

    @Test
    void update_ok() throws Exception {
        SubjectPOJO subjectPOJO = new SubjectPOJO(null, "Test1");
        Subject subject = new Subject(1L, "Test1", new ArrayList<>());

        subjectService.update(1L, subjectPOJO);
        verify(subjectRepository).save(subject);
    }
}