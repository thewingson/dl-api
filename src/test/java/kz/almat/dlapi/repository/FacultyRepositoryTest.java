package kz.almat.dlapi.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import kz.almat.dlapi.model.Faculty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author Almat_Rakhmetolla on 06.03.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@TestPropertySource("/application-test.properties")
@Transactional
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.INSERT)
    public void findAll_success() throws Exception {
        List<Faculty> faculties = facultyRepository.findAll();
        assertEquals(3, faculties.size());
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.INSERT)
    public void findById_success() throws Exception {
        Optional<Faculty> faculty = facultyRepository.findById(1L);
        assertEquals(new Faculty(1L, "Test1", new HashSet<>()).toString(), faculty.get().toString());
    }

    @Test
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-save-one.xml", table = "faculty")
    public void saveOne_success() throws Exception {
        facultyRepository.save(new Faculty(null, "Test4", new HashSet<>()));
        facultyRepository.findAll();
    }

    @Test
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-save-all.xml", table = "faculty")
    public void saveAll_success() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(1L, "Test4", new HashSet<>()));
        faculties.add(new Faculty(2L, "Test5", new HashSet<>()));
        facultyRepository.saveAll(faculties);
        List<Faculty> all = facultyRepository.findAll();
        System.out.println(all);
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-delete-by-id.xml", table = "faculty")
    public void deleteById_success() throws Exception {
        facultyRepository.deleteById(2L);
        facultyRepository.findAll();
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.INSERT)
    public void deleteAll_success() throws Exception {
        facultyRepository.deleteAll();
        List<Faculty> faculties = facultyRepository.findAll();
        assertEquals(0, faculties.size());
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-update.xml", table = "faculty")
    public void updateById_success() throws Exception {
        facultyRepository.save(new Faculty(1L, "Test10", new HashSet<>()));
        facultyRepository.findAll();
    }

    @AfterEach
    void tearDown() throws Exception {
    }
}