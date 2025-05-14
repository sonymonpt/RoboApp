package org.example.robobob.service;

import org.example.robobob.service.processor.BasicQuestionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BasicQuestionProcessorTest {

    @Mock
    private QuestionSource questionSource;

    private BasicQuestionProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new BasicQuestionProcessor(questionSource);
    }

    @Test
    void testCanProcessValidQuestion() {
        when(questionSource.getQuestions()).thenReturn(Map.of("What is your name", "RoboBob"));

        assertTrue(processor.canProcess("What is your name"));
        verify(questionSource).getQuestions();
    }

    @Test
    void testCanProcessInvalidQuestion() {
        when(questionSource.getQuestions()).thenReturn(Map.of("What is your name", "RoboBob"));

        assertFalse(processor.canProcess("Unknown question"));
        verify(questionSource).getQuestions();
    }

    @Test
    void testProcessValidQuestion() {
        when(questionSource.getQuestions()).thenReturn(Map.of("What is your name", "RoboBob"));

        String response = processor.process("What is your name");
        assertEquals("RoboBob", response);
        verify(questionSource).getQuestions();
    }

    @Test
    void testProcessInvalidQuestion() {
        when(questionSource.getQuestions()).thenReturn(Map.of("What is your name", "RoboBob"));

        String response = processor.process("Unknown question");
        assertEquals("I don't understand the question.", response);
        verify(questionSource).getQuestions();
    }
}