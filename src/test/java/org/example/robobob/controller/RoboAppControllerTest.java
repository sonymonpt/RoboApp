package org.example.robobob.controller;

import org.example.robobob.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoboAppControllerTest {

    @Test
    void testAskQuestion() {
        QuestionService mockService = Mockito.mock(QuestionService.class);
        when(mockService.processQuestion("What is your name")).thenReturn("RoboBob");

        RoboAppController controller = new RoboAppController();
        controller.questionService = mockService;

        ResponseEntity<String> response = controller.askQuestion("What is your name");
        assertEquals("RoboBob", response.getBody());
        verify(mockService, times(1)).processQuestion("What is your name");
    }
}