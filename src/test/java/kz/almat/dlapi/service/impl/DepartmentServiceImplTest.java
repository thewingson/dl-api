package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.DepartmentPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * @author almat_rakhmetolla on 10.03.2020
 * <p>
 * Unit test for {@link DepartmentService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void create_ok() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        Department department = new Department(null, "Test1", "Test11", faculty, new HashSet<>());

        when(facultyRepository.findById(departmentPOJO.getFacultyId())).thenReturn(Optional.of(faculty));
        departmentService.create(departmentPOJO);
        verify(departmentRepository).save(department);
    }

    @Test
    void create_facultyNotFound() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);

        when(facultyRepository.findById(departmentPOJO.getFacultyId())).thenReturn(Optional.empty());
        departmentService.create(departmentPOJO);
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    void createAll_ok() throws Exception {
        List<DepartmentPOJO> departmentPOJOS = new ArrayList<>();
        departmentPOJOS.add(new DepartmentPOJO(null, "Test1", "Test11", 1L));
        departmentPOJOS.add(new DepartmentPOJO(null, "Test2", "Test22", 2L));
        Faculty faculty1 = new Faculty();
        faculty1.setId(1L);
        Faculty faculty2 = new Faculty();
        faculty2.setId(2L);
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(null, "Test1", "Test11", faculty1, new HashSet<>()));
        departments.add(new Department(null, "Test2", "Test22", faculty2, new HashSet<>()));

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty1));
        when(facultyRepository.findById(2L)).thenReturn(Optional.of(faculty2));
        departmentService.createAll(departmentPOJOS);
        verify(departmentRepository).saveAll(departments);
    }

    @Test
    void createAll_facultyNotFound() throws Exception {
        List<DepartmentPOJO> departmentPOJOS = new ArrayList<>();
        departmentPOJOS.add(new DepartmentPOJO(null, "Test1", "Test11", 1L));
        departmentPOJOS.add(new DepartmentPOJO(null, "Test2", "Test22", 2L));
        List<Department> departments = new ArrayList<>();

        when(facultyRepository.findById(anyLong())).thenReturn(Optional.empty());
        departmentService.createAll(departmentPOJOS);
        verify(departmentRepository).saveAll(departments);
    }

    @Test
    void update_ok() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        Department department = new Department(1L, "Test1", "Test11", faculty, new HashSet<>());

        when(facultyRepository.findById(departmentPOJO.getFacultyId())).thenReturn(Optional.of(faculty));
        departmentService.update(1L, departmentPOJO);
        verify(departmentRepository).save(department);
    }

    @Test
    void update_facultyNotFound() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);

        when(facultyRepository.findById(departmentPOJO.getFacultyId())).thenReturn(Optional.empty());
        departmentService.update(1L, departmentPOJO);
        verify(departmentRepository, never()).save(any(Department.class));
    }
}