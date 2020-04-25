package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.pojo.StudentPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.StudentRepository;
import kz.almat.dlapi.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author almat_rakhmetolla on 10.03.2020
 * <p>
 * Unit test for {@link StudentService}
 */

class StudentServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private GroupRepository groupRepository;

    @Test
    void create_ok() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "test111", 1L);
        Group group = new Group();
        group.setId(1L);
        Student student = new Student(null, "Test1", "Test11", "Test111", group, new HashSet<>());

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        studentService.create(studentPOJO);
        verify(studentRepository).save(student);
    }

    @Test
    void create_groupNotFound() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "test111", 1L);

        when(groupRepository.findById(1L)).thenReturn(Optional.empty());
        studentService.create(studentPOJO);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void createAll_ok() throws Exception {
        List<StudentPOJO> studentPOJOS = new ArrayList<>();
        studentPOJOS.add(new StudentPOJO(null, "Test1", "Test11", "test111", 1L));
        studentPOJOS.add(new StudentPOJO(null, "Test2", "Test22", "test222", 2L));
        Group group1 = new Group();
        group1.setId(1L);
        Group group2 = new Group();
        group2.setId(2L);
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, "Test1", "Test11", "Test111", group1, new HashSet<>()));
        students.add(new Student(null, "Test1", "Test11", "Test111", group2, new HashSet<>()));

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group1));
        when(groupRepository.findById(2L)).thenReturn(Optional.of(group2));
        studentService.createAll(studentPOJOS);
        verify(studentRepository).saveAll(students);
    }

    @Test
    void createAll_groupNotFound() throws Exception {
        List<StudentPOJO> studentPOJOS = new ArrayList<>();
        studentPOJOS.add(new StudentPOJO(null, "Test1", "Test11", "test111", 1L));
        studentPOJOS.add(new StudentPOJO(null, "Test2", "Test22", "test222", 2L));
        List<Student> students = new ArrayList<>();

        when(groupRepository.findById(anyLong())).thenReturn(Optional.empty());
        studentService.createAll(studentPOJOS);
        verify(studentRepository).saveAll(students);
    }

    @Test
    void update_ok() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "test111", 1L);
        Group group = new Group();
        group.setId(1L);
        Student student = new Student(1L, "Test1", "Test11", "Test111", group, new HashSet<>());

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        studentService.update(1L, studentPOJO);
        verify(studentRepository).save(student);
    }

    @Test
    void update_groupNotFound() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "test111", 1L);

        when(groupRepository.findById(1L)).thenReturn(Optional.empty());
        studentService.update(1L, studentPOJO);
        verify(studentRepository, never()).save(any(Student.class));
    }
}