package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.model.TaskDetail;
import kz.almat.dlapi.pojo.TaskPOJO;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.TaskRepository;
import kz.almat.dlapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author almat_rakhmetolla on 12.03.2020
 * <p>
 * Unit test for {@link TaskService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private SubjectClassRepository subjectClassRepository;

    @Test
    void create_ok() throws Exception {
        List<Long> subjectClassesLong = new ArrayList<>();
        List<SubjectClass> subjectClasses = new ArrayList<>();
        TaskPOJO taskPOJO = new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), subjectClassesLong);
        TaskDetail taskDetail = new TaskDetail("Test1", "Test11", new Timestamp(1));
        Task task = new Task(null, 10, taskDetail, new HashSet<>(subjectClasses), new HashSet<>());

        when(subjectClassRepository.findAllById(taskPOJO.getSubjectClasses())).thenReturn(subjectClasses);
        taskService.create(taskPOJO);
        verify(taskRepository).save(task);
    }

    @Test
    void createAll_ok() throws Exception {
        List<Long> subjectClassesLong1 = new ArrayList<>();
        List<Long> subjectClassesLong2 = new ArrayList<>();
        List<SubjectClass> subjectClasses1 = new ArrayList<>();
        List<SubjectClass> subjectClasses2 = new ArrayList<>();
        List<TaskPOJO> taskPOJOS = new ArrayList<>();
        taskPOJOS.add(new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), subjectClassesLong1));
        taskPOJOS.add(new TaskPOJO(null, 20, "Test2", "Test22", new Timestamp(2), subjectClassesLong2));
        TaskDetail taskDetail1 = new TaskDetail("Test1", "Test11", new Timestamp(1));
        TaskDetail taskDetail2 = new TaskDetail("Test2", "Test22", new Timestamp(2));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(null, 10, taskDetail1, new HashSet<>(subjectClasses1), new HashSet<>()));
        tasks.add(new Task(null, 20, taskDetail2, new HashSet<>(subjectClasses2), new HashSet<>()));

        when(subjectClassRepository.findAllById(subjectClassesLong1)).thenReturn(subjectClasses1);
        when(subjectClassRepository.findAllById(subjectClassesLong2)).thenReturn(subjectClasses2);
        taskService.createAll(taskPOJOS);
        verify(taskRepository).saveAll(tasks);
    }

    @Test
    void update_ok() {
        List<Long> subjectClassesLong = new ArrayList<>();
        List<SubjectClass> subjectClasses = new ArrayList<>();
        TaskPOJO taskPOJO = new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), subjectClassesLong);
        TaskDetail taskDetail = new TaskDetail("Test1", "Test11", new Timestamp(1));
        Task task = new Task(1L, 10, taskDetail, new HashSet<>(subjectClasses), new HashSet<>());

        when(subjectClassRepository.findAllById(taskPOJO.getSubjectClasses())).thenReturn(subjectClasses);
        taskService.update(1L, taskPOJO);
        verify(taskRepository).save(task);
    }
}