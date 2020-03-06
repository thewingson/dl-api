package kz.almat.dlapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Almat_Rakhmetolla on 06.03.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@DatabaseSetup(value = "/data.xml", type = DatabaseOperation.INSERT)
//@TestExecutionListeners({
//        TransactionalTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DbUnitTestExecutionListener.class
//})
@TestPropertySource("/application-test.properties")
@Transactional
class FacultyRepositoryTest {

//    protected DataSourceDatabaseTester dataSourceDatabaseTester;

//    @Autowired
//    protected DataSource dataSource;

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() throws Exception{
//        dataSourceDatabaseTester = new DataSourceDatabaseTester(dataSource);
//        IDataSet dataSet = new FlatXmlDataSet(getClass().getResource("/data.xml"));
//        dataSourceDatabaseTester.setDataSet(dataSet);
//        dataSourceDatabaseTester.onSetup();
    }

    @Test
    public void findAll() {
//        List<Faculty> all = facultyRepository.findAll();
//        System.out.println(all);
//
//        Optional<Faculty> faculty = facultyRepository.findById(1L);
//        assertTrue(faculty.isPresent());
    }

    @AfterEach
    void tearDown() {
    }
}