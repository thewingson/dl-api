package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Teacher;
import kz.almat.dlapi.pojo.TeacherPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * @author almat_rakhmetolla on 10.03.2020
 * <p>
 * Unit test for {@link TeacherService}
 */

class TeacherServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void create_ok() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);
        Department department = new Department();
        department.setId(1L);
        Teacher teacher = new Teacher(null, "Test1", "Test11", "Test111", department, new ArrayList<>());

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        teacherService.create(teacherPOJO);
        verify(teacherRepository).save(teacher);
    }

    @Test
    void create_departmentNotFound() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
        teacherService.create(teacherPOJO);
        verify(teacherRepository, never()).save(any(Teacher.class));
    }

    @Test
    void createAll_ok() throws Exception {
        List<TeacherPOJO> teacherPOJOS = new ArrayList<>();
        teacherPOJOS.add(new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L));
        teacherPOJOS.add(new TeacherPOJO(null, "Test2", "Test22", "Test222", 2L));
        Department department1 = new Department();
        department1.setId(1L);
        Department department2 = new Department();
        department2.setId(2L);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(null, "Test1", "Test11", "Test111", department1, new ArrayList<>()));
        teachers.add(new Teacher(null, "Test2", "Test22", "Test222", department1, new ArrayList<>()));

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));
        when(departmentRepository.findById(2L)).thenReturn(Optional.of(department2));
        teacherService.createAll(teacherPOJOS);
        verify(teacherRepository).saveAll(teachers);
    }

    @Test
    void createAll_departmentNotFound() throws Exception {
        List<TeacherPOJO> teacherPOJOS = new ArrayList<>();
        teacherPOJOS.add(new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L));
        teacherPOJOS.add(new TeacherPOJO(null, "Test2", "Test22", "Test222", 2L));
        List<Teacher> teachers = new ArrayList<>();

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        teacherService.createAll(teacherPOJOS);
        verify(teacherRepository).saveAll(teachers);
    }

    @Test
    void update_ok() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);
        Department department = new Department();
        department.setId(1L);
        Teacher teacher = new Teacher(1L, "Test1", "Test11", "Test111", department, new ArrayList<>());

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        teacherService.update(1L, teacherPOJO);
        verify(teacherRepository).save(teacher);
    }

    @Test
    void update_departmentNotFound() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
        teacherService.update(1L, teacherPOJO);
        verify(teacherRepository, never()).save(any(Teacher.class));
    }
}