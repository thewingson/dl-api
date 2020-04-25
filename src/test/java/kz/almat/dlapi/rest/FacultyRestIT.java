package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
 * @author almat_rakhmetolla on 12.03.2020
 * <p>
 * Integration test for {@link FacultyRest}
 */

class FacultyRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void getAll_ok() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(1L, "Test1", new HashSet<>()));
        faculties.add(new Faculty(2L, "Test2", new HashSet<>()));

        when(facultyRepository.findAll()).thenReturn(faculties);
        mockMvc.perform(get("/faculty"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Test2")));
        verify(facultyRepository).findAll();
    }

    @Test
    void getOne_ok() throws Exception {
        Faculty faculty = new Faculty(1L, "Test1", new HashSet<>());

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        mockMvc.perform(get("/faculty/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test1")));
        verify(facultyRepository).findById(1L);
    }

    @Test
    void add_ok() throws Exception {
        FacultyPOJO facultyPOJO = new FacultyPOJO(null, "Test1");

        mockMvc.perform(post("/faculty")
                .content(om.writeValueAsString(facultyPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facultyService).create(facultyPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<FacultyPOJO> facultyPOJOS = new ArrayList<>();
        facultyPOJOS.add(new FacultyPOJO(null, "Test1"));
        facultyPOJOS.add(new FacultyPOJO(null, "Test2"));

        mockMvc.perform(post("/faculty/all")
                .content(om.writeValueAsString(facultyPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facultyService).createAll(facultyPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        FacultyPOJO facultyPOJO = new FacultyPOJO(null, "Test1");

        mockMvc.perform(put("/faculty/1")
                .content(om.writeValueAsString(facultyPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facultyService).update(1L, facultyPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/faculty/all"))
                .andExpect(status().isOk());
        verify(facultyRepository).deleteAll();
    }
}