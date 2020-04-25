package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.dto.StudentDTO;
import kz.almat.dlapi.pojo.StudentPOJO;
import kz.almat.dlapi.repository.StudentRepository;
import kz.almat.dlapi.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
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
 * Integration test for {@link StudentRest}
 */

class StudentRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getAll_ok() throws Exception {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentDTOS.add(new StudentDTO(1L, "Test1", "Test11", "Test111", 1L, 1L, 1L));
        studentDTOS.add(new StudentDTO(2L, "Test2", "Test22", "Test222", 2L, 2L, 2L));

        when(studentRepository.findAllConvertedToDTO()).thenReturn(studentDTOS);
        mockMvc.perform(get("/student"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Test1")))
                .andExpect(jsonPath("$[0].lastName", is("Test11")))
                .andExpect(jsonPath("$[0].middleName", is("Test111")))
                .andExpect(jsonPath("$[0].groupId", is(1)))
                .andExpect(jsonPath("$[0].groupGrade", is(1)))
                .andExpect(jsonPath("$[0].groupListNumber", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Test2")))
                .andExpect(jsonPath("$[1].lastName", is("Test22")))
                .andExpect(jsonPath("$[1].middleName", is("Test222")))
                .andExpect(jsonPath("$[1].groupId", is(2)))
                .andExpect(jsonPath("$[1].groupGrade", is(2)))
                .andExpect(jsonPath("$[1].groupListNumber", is(2)));
        verify(studentRepository).findAllConvertedToDTO();
    }

    @Test
    void getOne_ok() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1L, "Test1", "Test11", "Test111", 1L, 1L, 1L);

        when(studentRepository.findByIdConvertedToDTO(1L)).thenReturn(Optional.of(studentDTO));
        mockMvc.perform(get("/student/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Test1")))
                .andExpect(jsonPath("$.lastName", is("Test11")))
                .andExpect(jsonPath("$.middleName", is("Test111")))
                .andExpect(jsonPath("$.groupId", is(1)))
                .andExpect(jsonPath("$.groupGrade", is(1)))
                .andExpect(jsonPath("$.groupListNumber", is(1)));
        verify(studentRepository).findByIdConvertedToDTO(1L);
    }

    @Test
    void add_ok() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "Test111", 1L);

        mockMvc.perform(post("/student")
                .content(om.writeValueAsString(studentPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(studentService).create(studentPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<StudentPOJO> studentPOJOS = new ArrayList<>();
        studentPOJOS.add(new StudentPOJO(null, "Test1", "Test11", "Test111", 1L));
        studentPOJOS.add(new StudentPOJO(null, "Test2", "Test22", "Test222", 2L));

        mockMvc.perform(post("/student/all")
                .content(om.writeValueAsString(studentPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(studentService).createAll(studentPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        StudentPOJO studentPOJO = new StudentPOJO(null, "Test1", "Test11", "Test111", 1L);

        mockMvc.perform(put("/student/1")
                .content(om.writeValueAsString(studentPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(studentService).update(1L, studentPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());
        verify(studentRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/student/all"))
                .andExpect(status().isOk());
        verify(studentRepository).deleteAll();
    }
}