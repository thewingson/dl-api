package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.DepartmentPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.service.DepartmentService;
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
 * @author almat_rakhmetolla on 13.03.2020
 * <p>
 * Integration test for {@link DepartmentRest}
 */

class DepartmentRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void getAll_ok() throws Exception {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1L, "Test1", "Test11", new Faculty(), new HashSet<>()));
        departments.add(new Department(2L, "Test2", "Test22", new Faculty(), new HashSet<>()));

        when(departmentRepository.findAll()).thenReturn(departments);
        mockMvc.perform(get("/department"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[0].code", is("Test11")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Test2")))
                .andExpect(jsonPath("$[1].code", is("Test22")));
        verify(departmentRepository).findAll();
    }

    @Test
    void getOne_ok() throws Exception {
        Department department = new Department(1L, "Test1", "Test11", new Faculty(), new HashSet<>());

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        mockMvc.perform(get("/department/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test1")))
                .andExpect(jsonPath("$.code", is("Test11")));
        verify(departmentRepository).findById(1L);
    }

    @Test
    void add_ok() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);

        mockMvc.perform(post("/department")
                .content(om.writeValueAsString(departmentPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(departmentService).create(departmentPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<DepartmentPOJO> departmentPOJOS = new ArrayList<>();
        departmentPOJOS.add(new DepartmentPOJO(null, "Test1", "Test11", 1L));
        departmentPOJOS.add(new DepartmentPOJO(null, "Test2", "Test22", 2L));

        mockMvc.perform(post("/department/all")
                .content(om.writeValueAsString(departmentPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(departmentService).createAll(departmentPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        DepartmentPOJO departmentPOJO = new DepartmentPOJO(null, "Test1", "Test11", 1L);

        mockMvc.perform(put("/department/1")
                .content(om.writeValueAsString(departmentPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(departmentService).update(1L, departmentPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/department/1"))
                .andExpect(status().isOk());
        verify(departmentRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/department/all"))
                .andExpect(status().isOk());
        verify(departmentRepository).deleteAll();
    }
}