package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.dto.SubjectClassDTO;
import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
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
 * Integration test for {@link SubjectClassRest}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SubjectClassRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectClassService subjectClassService;

    @MockBean
    private SubjectClassRepository subjectClassRepository;

    @Test
    void getAll_ok() throws Exception {
        List<SubjectClassDTO> subjectClassDTOS = new ArrayList<>();
        subjectClassDTOS.add(new SubjectClassDTO(1L, 1L, "Test1", 1L, 1L, 1L, 1L, "Test11", "Test111", "Test1111"));
        subjectClassDTOS.add(new SubjectClassDTO(2L, 2L, "Test2", 2L, 2L, 2L, 2L, "Test22", "Test222", "Test2222"));

        when(subjectClassRepository.findAllConvertedToDTO()).thenReturn(subjectClassDTOS);
        mockMvc.perform(get("/subject-class"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].subjectId", is(1)))
                .andExpect(jsonPath("$[0].subjectName", is("Test1")))
                .andExpect(jsonPath("$[0].groupId", is(1)))
                .andExpect(jsonPath("$[0].groupGrade", is(1)))
                .andExpect(jsonPath("$[0].groupNumber", is(1)))
                .andExpect(jsonPath("$[0].teacherId", is(1)))
                .andExpect(jsonPath("$[0].teacherFirstName", is("Test11")))
                .andExpect(jsonPath("$[0].teacherLastName", is("Test111")))
                .andExpect(jsonPath("$[0].teacherMiddleName", is("Test1111")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].subjectId", is(2)))
                .andExpect(jsonPath("$[1].subjectName", is("Test2")))
                .andExpect(jsonPath("$[1].groupId", is(2)))
                .andExpect(jsonPath("$[1].groupGrade", is(2)))
                .andExpect(jsonPath("$[1].groupNumber", is(2)))
                .andExpect(jsonPath("$[1].teacherId", is(2)))
                .andExpect(jsonPath("$[1].teacherFirstName", is("Test22")))
                .andExpect(jsonPath("$[1].teacherLastName", is("Test222")))
                .andExpect(jsonPath("$[1].teacherMiddleName", is("Test2222")));
        verify(subjectClassRepository).findAllConvertedToDTO();
    }

    @Test
    void getOne_ok() throws Exception {
        SubjectClassDTO subjectClassDTO = new SubjectClassDTO(1L, 1L, "Test1", 1L, 1L, 1L, 1L, "Test11", "Test111", "Test1111");

        when(subjectClassRepository.findByIdConvertedToDTO(1L)).thenReturn(Optional.of(subjectClassDTO));
        mockMvc.perform(get("/subject-class/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subjectId", is(1)))
                .andExpect(jsonPath("$.subjectName", is("Test1")))
                .andExpect(jsonPath("$.groupId", is(1)))
                .andExpect(jsonPath("$.groupGrade", is(1)))
                .andExpect(jsonPath("$.groupNumber", is(1)))
                .andExpect(jsonPath("$.teacherId", is(1)))
                .andExpect(jsonPath("$.teacherFirstName", is("Test11")))
                .andExpect(jsonPath("$.teacherLastName", is("Test111")))
                .andExpect(jsonPath("$.teacherMiddleName", is("Test1111")));
        verify(subjectClassRepository).findByIdConvertedToDTO(1L);
    }

    @Test
    void add_ok() throws Exception {
        SubjectClassPOJO subjectClassPOJO = new SubjectClassPOJO(null, 1L, 1L, 1L);

        mockMvc.perform(post("/subject-class")
                .content(om.writeValueAsString(subjectClassPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectClassService).create(subjectClassPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<SubjectClassPOJO> subjectClassPOJOS = new ArrayList<>();
        subjectClassPOJOS.add(new SubjectClassPOJO(null, 1L, 1L, 1L));
        subjectClassPOJOS.add(new SubjectClassPOJO(null, 2L, 2L, 2L));

        mockMvc.perform(post("/subject-class/all")
                .content(om.writeValueAsString(subjectClassPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectClassService).createAll(subjectClassPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        SubjectClassPOJO subjectClassPOJO = new SubjectClassPOJO(null, 1L, 1L, 1L);

        mockMvc.perform(put("/subject-class/1")
                .content(om.writeValueAsString(subjectClassPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectClassService).update(1L, subjectClassPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/subject-class/1"))
                .andExpect(status().isOk());
        verify(subjectClassRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/subject-class/all"))
                .andExpect(status().isOk());
        verify(subjectClassRepository).deleteAll();
    }
}