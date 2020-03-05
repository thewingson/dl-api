package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.HashSet;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

/**
 * @author Almat_Rakhmetolla on 05.03.2020
 *
 * Test on {@link kz.almat.dlapi.service.FacultyService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource("/application-test.properties")
//@ContextConfiguration(locations = {"/flywayContainerContext.xml"})
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
//@FlywayTest
//@Sql(value = {"/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/destroy-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
    void init() throws Exception{
        assertNotNull(facultyService);
    }

    @Test
    void create() throws Exception{
        FacultyPOJO facultyPOJO = new FacultyPOJO();
        facultyPOJO.setName("test1");

        Faculty faculty = new Faculty();
        faculty.setName(facultyPOJO.getName());

        facultyService.create(facultyPOJO);
        Mockito.verify(facultyRepository).save(faculty);
    }

    @Test
    void createAll() throws Exception{
    }

    @Test
    void update() throws Exception{
    }
}