package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.dto.TeacherDTO;
import kz.almat.dlapi.pojo.TeacherPOJO;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.TeacherService;
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
 * Integration test for {@link TeacherRest}
 */

class TeacherRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void getAll_ok() throws Exception {
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        teacherDTOS.add(new TeacherDTO(1L, "Test1", "Test11", "Test111", 1L, "Test1111", "Test11111"));
        teacherDTOS.add(new TeacherDTO(2L, "Test2", "Test22", "Test222", 2L, "Test2222", "Test22222"));

        when(teacherRepository.findAllConvertedToDTO()).thenReturn(teacherDTOS);
        mockMvc.perform(get("/teacher"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Test1")))
                .andExpect(jsonPath("$[0].lastName", is("Test11")))
                .andExpect(jsonPath("$[0].middleName", is("Test111")))
                .andExpect(jsonPath("$[0].departmentId", is(1)))
                .andExpect(jsonPath("$[0].departmentName", is("Test1111")))
                .andExpect(jsonPath("$[0].departmentCode", is("Test11111")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Test2")))
                .andExpect(jsonPath("$[1].lastName", is("Test22")))
                .andExpect(jsonPath("$[1].middleName", is("Test222")))
                .andExpect(jsonPath("$[1].departmentId", is(2)))
                .andExpect(jsonPath("$[1].departmentName", is("Test2222")))
                .andExpect(jsonPath("$[1].departmentCode", is("Test22222")));
        verify(teacherRepository).findAllConvertedToDTO();
    }

    @Test
    void getOne_ok() throws Exception {
        TeacherDTO teacherDTO = new TeacherDTO(1L, "Test1", "Test11", "Test111", 1L, "Test1111", "Test11111");

        when(teacherRepository.findByIdConvertedToDTO(1L)).thenReturn(Optional.of(teacherDTO));
        mockMvc.perform(get("/teacher/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Test1")))
                .andExpect(jsonPath("$.lastName", is("Test11")))
                .andExpect(jsonPath("$.middleName", is("Test111")))
                .andExpect(jsonPath("$.departmentId", is(1)))
                .andExpect(jsonPath("$.departmentName", is("Test1111")))
                .andExpect(jsonPath("$.departmentCode", is("Test11111")));
        verify(teacherRepository).findByIdConvertedToDTO(1L);
    }

    @Test
    void add_ok() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);

        mockMvc.perform(post("/teacher")
                .content(om.writeValueAsString(teacherPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(teacherService).create(teacherPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<TeacherPOJO> teacherPOJOS = new ArrayList<>();
        teacherPOJOS.add(new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L));
        teacherPOJOS.add(new TeacherPOJO(null, "Test2", "Test22", "Test222", 2L));

        mockMvc.perform(post("/teacher/all")
                .content(om.writeValueAsString(teacherPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(teacherService).createAll(teacherPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        TeacherPOJO teacherPOJO = new TeacherPOJO(null, "Test1", "Test11", "Test111", 1L);

        mockMvc.perform(put("/teacher/1")
                .content(om.writeValueAsString(teacherPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(teacherService).update(1L, teacherPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/teacher/1"))
                .andExpect(status().isOk());
        verify(teacherRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/teacher/all"))
                .andExpect(status().isOk());
        verify(teacherRepository).deleteAll();
    }
}