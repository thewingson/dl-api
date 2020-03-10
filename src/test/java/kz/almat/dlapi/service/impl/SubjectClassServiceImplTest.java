package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author almat_rakhmetolla on 10.03.2020
 *
 * Unit test for {@link SubjectClassService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class SubjectClassServiceImplTest {

    @Autowired
    private SubjectClassService subjectClassService;

    @MockBean
    private SubjectClassRepository subjectClassRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void create_ok() throws Exception {
        SubjectClassPOJO subjectClassPOJO = new SubjectClassPOJO(null, 1L, 1L, 1L);

        subjectClassService.create(subjectClassPOJO);
        verify(subjectClassRepository).saveByIDs(1L, 1L, 1L);
    }

    @Test
    void createAll_ok() throws Exception {
        List<SubjectClassPOJO> subjectClassPOJOS = new ArrayList<>();
        subjectClassPOJOS.add(new SubjectClassPOJO(null, 1L, 1L, 1L));
        subjectClassPOJOS.add(new SubjectClassPOJO(null, 2L, 2L, 2L));

        subjectClassService.createAll(subjectClassPOJOS);
        verify(subjectClassRepository).saveByIDs(1L, 1L, 1L);
        verify(subjectClassRepository).saveByIDs(2L, 2L, 2L);
    }

    @Test
    void update_ok() throws Exception {
        SubjectClassPOJO subjectClassPOJO = new SubjectClassPOJO(null, 1L, 1L, 1L);

        subjectClassService.update(1L, subjectClassPOJO);
        verify(subjectClassRepository).updateById(1L, 1L, 1L, 1L);
    }
}