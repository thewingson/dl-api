package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author almat_rakhmetolla on 10.03.2020
 *
 * Unit test for {@link FacultyService}
 */

class FacultyServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void create_ok() throws Exception{
        FacultyPOJO facultyPOJO = new FacultyPOJO(null, "Test1");
        Faculty faculty = new Faculty(null, "Test1", new HashSet<>());

        facultyService.create(facultyPOJO);
        verify(facultyRepository).save(faculty);
    }

    @Test
    void createAll_ok() throws Exception {
        List<FacultyPOJO> facultyPOJOS = new ArrayList<>();
        facultyPOJOS.add(new FacultyPOJO(null, "Test1"));
        facultyPOJOS.add(new FacultyPOJO(null, "Test2"));

        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(null, "Test1", new HashSet<>()));
        faculties.add(new Faculty(null, "Test2", new HashSet<>()));

        facultyService.createAll(facultyPOJOS);
        verify(facultyRepository).saveAll(faculties);
    }

    @Test
    void update_ok() throws Exception {
        FacultyPOJO facultyPOJO = new FacultyPOJO(null, "Test1");
        Faculty faculty = new Faculty(1L, "Test1", new HashSet<>());

        facultyService.update(1L, facultyPOJO);
        verify(facultyRepository).save(faculty);
    }
}