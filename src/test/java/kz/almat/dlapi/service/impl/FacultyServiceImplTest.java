package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author Almat_Rakhmetolla on 05.03.2020
 *
 * Test on {@link kz.almat.dlapi.service.FacultyService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class FacultyServiceImplTest {

    @Autowired
    private FacultyService facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    private Department department;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() throws Exception{
        facultyService.create(new FacultyPOJO());
        Mockito.verify(facultyRepository).save(new Faculty(anyLong(), anyString(), any(HashSet.class)));
    }

    @Test
    void createAll() throws Exception{
    }

    @Test
    void update() throws Exception{
    }
}