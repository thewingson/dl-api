package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.pojo.SubjectPOJO;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.service.SubjectService;
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
 * Integration test for {@link SubjectRest}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SubjectRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private SubjectRepository subjectRepository;


    @Test
    void getAll_ok() throws Exception {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1L, "Test1", new ArrayList<>()));
        subjects.add(new Subject(2L, "Test2", new ArrayList<>()));

        when(subjectRepository.findAll()).thenReturn(subjects);
        mockMvc.perform(get("/subject"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Test2")));
        verify(subjectRepository).findAll();
    }

    @Test
    void getOne_ok() throws Exception {
        Subject subject = new Subject(1L, "Test1", new ArrayList<>());

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        mockMvc.perform(get("/subject/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test1")));
        verify(subjectRepository).findById(1L);
    }

    @Test
    void add_ok() throws Exception {
        SubjectPOJO subjectPOJO = new SubjectPOJO(null, "Test1");

        mockMvc.perform(post("/subject")
                .content(om.writeValueAsString(subjectPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectService).create(subjectPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<SubjectPOJO> subjectPOJOS = new ArrayList<>();
        subjectPOJOS.add(new SubjectPOJO(null, "Test1"));
        subjectPOJOS.add(new SubjectPOJO(null, "Test2"));

        mockMvc.perform(post("/subject/all")
                .content(om.writeValueAsString(subjectPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectService).createAll(subjectPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        SubjectPOJO subjectPOJO = new SubjectPOJO(null, "Test1");

        mockMvc.perform(put("/subject/1")
                .content(om.writeValueAsString(subjectPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subjectService).update(1L, subjectPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/subject/1"))
                .andExpect(status().isOk());
        verify(subjectRepository).deleteById(1L);
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(delete("/subject/all"))
                .andExpect(status().isOk());
        verify(subjectRepository).deleteAll();
    }
}