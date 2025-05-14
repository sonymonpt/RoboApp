package com.example.integration;

import org.example.robobob.RoboAppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RoboAppApplication.class)
@AutoConfigureMockMvc
class RoboAppControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAskKnownQuestion() throws Exception {
        mockMvc.perform(post("/api/ask")
                        .content("What is your name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("RoboBob"));
    }

    @Test
    void testAskUnknownQuestion() throws Exception {
        mockMvc.perform(post("/api/ask")
                        .content("Unknown question")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("I don't understand the question. Please ask a different question."));
    }
}