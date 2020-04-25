package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.model.TaskDetail;
import kz.almat.dlapi.pojo.TaskPOJO;
import kz.almat.dlapi.repository.TaskRepository;
import kz.almat.dlapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author almat_rakhmetolla on 13.03.2020
 * <p>
 * Integration test for {@link TaskRest}
 */

class TaskRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void getAll_ok() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, 10, new TaskDetail("Test1", "Test11", new Timestamp(1)), new HashSet<>(), new HashSet<>()));
        tasks.add(new Task(2L, 20, new TaskDetail("Test2", "Test22", new Timestamp(2)), new HashSet<>(), new HashSet<>()));

        when(taskRepository.findAll()).thenReturn(tasks);
        mockMvc.perform(get("/task"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].value", is(10)))
                .andExpect(jsonPath("$[0].taskDetail.topic", is("Test1")))
                .andExpect(jsonPath("$[0].taskDetail.description", is("Test11")))
                .andExpect(jsonPath("$[0].taskDetail.deadline", is("1970-01-01T00:00:00.001+0000")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].value", is(20)))
                .andExpect(jsonPath("$[1].taskDetail.topic", is("Test2")))
                .andExpect(jsonPath("$[1].taskDetail.description", is("Test22")))
                .andExpect(jsonPath("$[1].taskDetail.deadline", is("1970-01-01T00:00:00.002+0000")));
        verify(taskRepository).findAll();
    }

    @Test
    void getOne_ok() throws Exception {
        Task task = new Task(1L, 10, new TaskDetail("Test1", "Test11", new Timestamp(1)), new HashSet<>(), new HashSet<>());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        mockMvc.perform(get("/task/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.value", is(10)))
                .andExpect(jsonPath("$.taskDetail.topic", is("Test1")))
                .andExpect(jsonPath("$.taskDetail.description", is("Test11")))
                .andExpect(jsonPath("$.taskDetail.deadline", is("1970-01-01T00:00:00.001+0000")));
        verify(taskRepository).findById(1L);
    }

    @Test
    void add_ok() throws Exception {
        TaskPOJO taskPOJO = new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), new ArrayList<>());

        mockMvc.perform(post("/task")
                .content(om.writeValueAsString(taskPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(taskService).create(taskPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<TaskPOJO> taskPOJOS = new ArrayList<>();
        taskPOJOS.add(new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), new ArrayList<>()));
        taskPOJOS.add(new TaskPOJO(null, 20, "Test2", "Test22", new Timestamp(2), new ArrayList<>()));

        mockMvc.perform(post("/task/all")
                .content(om.writeValueAsString(taskPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(taskService).createAll(taskPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        TaskPOJO taskPOJO = new TaskPOJO(null, 10, "Test1", "Test11", new Timestamp(1), new ArrayList<>());

        mockMvc.perform(put("/task/1")
                .content(om.writeValueAsString(taskPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(taskService).update(1L, taskPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/task/1"))
                .andExpect(status().isOk());
        verify(taskRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/task/all"))
                .andExpect(status().isOk());
        verify(taskRepository).deleteAll();
    }
}