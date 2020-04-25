package kz.almat.dlapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.almat.dlapi.model.Answer;
import kz.almat.dlapi.model.AnswerDetail;
import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.pojo.AnswerPOJO;
import kz.almat.dlapi.repository.AnswerRepository;
import kz.almat.dlapi.service.AnswerService;
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
 * Integration test for {@link AnswerRest}
 */

class AnswerRestIT extends AbstractRestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerRepository answerRepository;

    @Test
    void getAll_ok() throws Exception {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L, new Student(), new Task(), new AnswerDetail("Test1")));
        answers.add(new Answer(2L, new Student(), new Task(), new AnswerDetail("Test2")));

        when(answerRepository.findAll()).thenReturn(answers);
        mockMvc.perform(get("/answer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].answerDetail.message", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].answerDetail.message", is("Test2")));
        verify(answerRepository).findAll();
    }

    @Test
    void getOne_ok() throws Exception {
        Answer answer = new Answer(1L, new Student(), new Task(), new AnswerDetail("Test1"));

        when(answerRepository.findById(1L)).thenReturn(Optional.of(answer));
        mockMvc.perform(get("/answer/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.answerDetail.message", is("Test1")));
        verify(answerRepository).findById(1L);
    }

    @Test
    void add_ok() throws Exception {
        AnswerPOJO answerPOJO = new AnswerPOJO(null, 1L, 1L, "Test1");

        mockMvc.perform(post("/answer")
                .content(om.writeValueAsString(answerPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(answerService).create(answerPOJO);
    }

    @Test
    void addAll_ok() throws Exception {
        List<AnswerPOJO> answerPOJOS = new ArrayList<>();
        answerPOJOS.add(new AnswerPOJO(null, 1L, 1L, "Test1"));
        answerPOJOS.add(new AnswerPOJO(null, 2L, 2L, "Test2"));

        mockMvc.perform(post("/answer/all")
                .content(om.writeValueAsString(answerPOJOS))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(answerService).createAll(answerPOJOS);
    }

    @Test
    void edit_ok() throws Exception {
        AnswerPOJO answerPOJO = new AnswerPOJO(null, 1L, 1L, "Test1");

        mockMvc.perform(put("/answer/1")
                .content(om.writeValueAsString(answerPOJO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(answerService).update(1L, answerPOJO);
    }

    @Test
    void delete_ok() throws Exception {
        mockMvc.perform(delete("/answer/1"))
                .andExpect(status().isOk());
        verify(answerRepository).deleteById(1L);
    }

    @Test
    void deleteAll_ok() throws Exception {
        mockMvc.perform(delete("/answer/all"))
                .andExpect(status().isOk());
        verify(answerRepository).deleteAll();
    }
}