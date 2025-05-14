package org.example.robobob.service;

import org.example.robobob.service.processor.*;
import org.example.robobob.util.ArithmeticEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class QuestionProcessorFactoryTest {

    @Mock
    ArithmeticEvaluator evaluator;
    @Mock
    QuestionSource questionSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetProcessorForBasicQuestion() {
        // Mock the behavior of questionSource.getQuestions
        when(questionSource.getQuestions()).thenReturn(Map.of("What is your name", "RoboBob"));

        QuestionProcessorFactory factory = new QuestionProcessorFactory(evaluator, questionSource);
        QuestionProcessor processor = factory.getProcessor("What is your name");

        assertInstanceOf(BasicQuestionProcessor.class, processor);
    }

    @Test
    void testGetProcessorForArithmeticExpression() {
        // Mock the behavior of evaluator.isArithmeticExpression
        when(evaluator.isArithmeticExpression("2+2")).thenReturn(true);

        QuestionProcessorFactory factory = new QuestionProcessorFactory(evaluator, questionSource);
        QuestionProcessor processor = factory.getProcessor("2+2");

        assertInstanceOf(ArithmeticQuestionProcessor.class, processor);
    }

    @Test
    void testGetProcessorForInvalidQuestion() {
        QuestionProcessorFactory factory = new QuestionProcessorFactory(evaluator, questionSource);
        QuestionProcessor processor = factory.getProcessor("Unknown question");
        assertInstanceOf(DefaultQuestionProcessor.class, processor);
    }
}