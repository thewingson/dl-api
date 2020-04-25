package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.dto.GroupDTO;
import kz.almat.dlapi.pojo.GroupPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.service.GroupService;
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
 * Integration test for {@link GroupRest}
 */

class GroupRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @Test
    void getAll_ok() throws Exception {
        List<GroupDTO> groupDTOS = new ArrayList<>();
        groupDTOS.add(new GroupDTO(1L, 1L, 1L, 1L, "Test1"));
        groupDTOS.add(new GroupDTO(2L, 2L, 2L, 2L, "Test2"));

        when(groupRepository.findAllConvertedToDTO()).thenReturn(groupDTOS);
        mockMvc.perform(get("/group"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].grade", is(1)))
                .andExpect(jsonPath("$[0].listNumber", is(1)))
                .andExpect(jsonPath("$[0].departmentId", is(1)))
                .andExpect(jsonPath("$[0].departmentName", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].grade", is(2)))
                .andExpect(jsonPath("$[1].listNumber", is(2)))
                .andExpect(jsonPath("$[1].departmentId", is(2)))
                .andExpect(jsonPath("$[1].departmentName", is("Test2")));
        verify(groupRepository).findAllConvertedToDTO();
    }

    @Test
    void getOne_ok() throws Exception {
        GroupDTO groupDTO = new GroupDTO(1L, 1L, 1L, 1L, "Test1");

        when(groupRepository.findByIdConvertedToDTO(1L)).thenReturn(Optional.of(groupDTO));
        mockMvc.perform(get("/group/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.grade", is(1)))
                .andExpect(jsonPath("$.listNumber", is(1)))
                .andExpect(jsonPath("$.departmentId", is(1)))
                .andExpect(jsonPath("$.departmentName", is("Test1")));
        verify(groupRepository).findByIdConvertedToDTO(1L);
    }

    @Test
    void add_ok() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);

        mockMvc.perform(post("/group")
                .content(om.writeValueAsString(groupPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(groupService).create(groupPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<GroupPOJO> groupPOJOS = new ArrayList<>();
        groupPOJOS.add(new GroupPOJO(null, 1L, 1L, 1L));
        groupPOJOS.add(new GroupPOJO(null, 2L, 2L, 2L));

        mockMvc.perform(post("/group/all")
                .content(om.writeValueAsString(groupPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(groupService).createAll(groupPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);

        mockMvc.perform(put("/group/1")
                .content(om.writeValueAsString(groupPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(groupService).update(1L, groupPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/group/1"))
                .andExpect(status().isOk());
        verify(groupRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/group/all"))
                .andExpect(status().isOk());
        verify(groupRepository).deleteAll();
    }
}