package com.juan.referenceapi.controller;

import com.juan.referenceapi.dto.CreateReferenceRequest;
import com.juan.referenceapi.dto.ReferenceResponse;
import com.juan.referenceapi.service.ReferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReferenceController.class)
class ReferenceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ReferenceService referenceService;

    @Test
    void getAll_returnsListOfReferences() throws Exception {
        when(referenceService.findAll()).thenReturn(List.of(
                new ReferenceResponse(1L, "Deep Learning", "LeCun", "Nature", 2015, 50000, true)
        ));

        mockMvc.perform(get("/references"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Deep Learning"))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void create_withValidRequest_returns201() throws Exception {
        CreateReferenceRequest request = new CreateReferenceRequest(
                "Deep Learning", "LeCun", "Nature", 2015, 50000, true);

        when(referenceService.create(any())).thenReturn(
                new ReferenceResponse(1L, "Deep Learning", "LeCun", "Nature", 2015, 50000, true));

        mockMvc.perform(post("/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void create_withInvalidRequest_returns400() throws Exception {
        mockMvc.perform(post("/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\",\"authors\":\"\",\"journalId\":\"\",\"year\":1800}"))
                .andExpect(status().isBadRequest());
    }
}