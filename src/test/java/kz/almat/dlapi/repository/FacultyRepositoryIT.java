package kz.almat.dlapi.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import kz.almat.dlapi.model.Faculty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author Almat_Rakhmetolla on 06.03.2020
 * <p>
 * Integration test for {@link FacultyRepository}
 */

class FacultyRepositoryIT extends AbstractRepositoryIT {

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void findAll_success() throws Exception {
        List<Faculty> faculties = facultyRepository.findAll();
        assertEquals(3, faculties.size());
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void findById_success() throws Exception {
        Optional<Faculty> faculty = facultyRepository.findById(1L);
        assertEquals(new Faculty(1L, "Test1", new HashSet<>()).toString(), faculty.get().toString());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-save-one.xml", table = "faculty")
    public void saveOne_success() throws Exception {
        facultyRepository.save(new Faculty(null, "Test4", new HashSet<>()));
        List<Faculty> all = facultyRepository.findAll();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-save-all.xml", table = "faculty")
    public void saveAll_success() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(null, "Test4", new HashSet<>()));
        faculties.add(new Faculty(null, "Test5", new HashSet<>()));
        facultyRepository.saveAll(faculties);
        List<Faculty> all = facultyRepository.findAll();
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-delete-by-id.xml", table = "faculty")
    public void deleteById_success() throws Exception {
        facultyRepository.deleteById(2L);
        facultyRepository.findAll();
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void deleteAll_success() throws Exception {
        facultyRepository.deleteAll();
        List<Faculty> faculties = facultyRepository.findAll();
        assertEquals(0, faculties.size());
    }

    @Test
    @DatabaseSetup(value = "/db/dbunit/faculty/init.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/db/dbunit/faculty/after-update.xml", table = "faculty")
    public void updateById_success() throws Exception {
        facultyRepository.save(new Faculty(1L, "Test10", new HashSet<>()));
        facultyRepository.findAll();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

}