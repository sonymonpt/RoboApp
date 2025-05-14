package org.example.robobob.service;

import org.example.robobob.service.processor.ArithmeticQuestionProcessor;
import org.example.robobob.service.processor.QuestionProcessor;
import org.example.robobob.service.processor.QuestionProcessorFactory;
import org.example.robobob.util.ArithmeticEvaluator;
import org.example.robobob.util.QuestionConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuestionServiceTest {

    @Mock
    ArithmeticEvaluator evaluator;
    @Mock
    QuestionProcessorFactory questionProcessorFactory;

    @InjectMocks
    QuestionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testProcessBasicQuestion() {
        // Mock a QuestionProcessor
        QuestionProcessor mockProcessor = Mockito.mock(QuestionProcessor.class);
        when(mockProcessor.process("What is your name")).thenReturn("RoboBob");

        // Configure the factory to return the mock processor
        when(questionProcessorFactory.getProcessor("What is your name")).thenReturn(mockProcessor);

        // Test the service
        String response = service.processQuestion("What is your name");
        assertEquals("RoboBob", response);

        // Verify interactions
        verify(questionProcessorFactory).getProcessor("What is your name");
        verify(mockProcessor).process("What is your name");
    }


    @Test
    void testProcessArithmeticExpression() throws ScriptException {
        // Mock ArithmeticEvaluator
        ArithmeticEvaluator mockEvaluator = Mockito.mock(ArithmeticEvaluator.class);
        when(mockEvaluator.isArithmeticExpression("2+2")).thenReturn(true);
        when(mockEvaluator.evaluate("2+2")).thenReturn(4.0);

        ArithmeticQuestionProcessor processor = new ArithmeticQuestionProcessor(mockEvaluator);
        // Test the processor
        String response = processor.process("2+2");
        assertEquals("4.0", response);

        // Verify interactions
        verify(mockEvaluator).isArithmeticExpression("2+2");
        verify(mockEvaluator).evaluate("2+2");
    }

    @Test
    void testProcessInvalidQuestion() {
        // Mock a QuestionProcessor
        QuestionProcessor mockProcessor = Mockito.mock(QuestionProcessor.class);
        when(mockProcessor.process("Unknown question"))
                .thenReturn("I don't understand the question. Please ask a different question.");

        // Configure the factory to return the mock processor
        when(questionProcessorFactory.getProcessor("Unknown question")).thenReturn(mockProcessor);

        // Test the service
        String response = service.processQuestion("Unknown question");
        assertEquals("I don't understand the question. Please ask a different question.", response);

        // Verify interactions
        verify(questionProcessorFactory).getProcessor("Unknown question");
        verify(mockProcessor).process("Unknown question");
    }

    @Test
    void testProcessNullQuestion() {
        // Mock a QuestionProcessor
        QuestionProcessor mockProcessor = Mockito.mock(QuestionProcessor.class);
        when(mockProcessor.process(null)).thenReturn("Invalid question");

        // Configure the factory to return the mock processor for null input
        when(questionProcessorFactory.getProcessor(null)).thenReturn(mockProcessor);

        // Test the service
        String response = service.processQuestion(null);
        assertEquals("Invalid question", response, "Null input should return 'Invalid question'");
    }

    @Test
    void testProcessEmptyQuestion() {
        // Mock a QuestionProcessor
        QuestionProcessor mockProcessor = Mockito.mock(QuestionProcessor.class);
        when(mockProcessor.process("")).thenReturn(QuestionConstants.INVALID_QUESTION);

        // Configure the factory to return the mock processor for empty input
        when(questionProcessorFactory.getProcessor("")).thenReturn(mockProcessor);

        // Test the service
        String response = service.processQuestion("");
        assertEquals("Invalid question", response, "Empty input should return 'Invalid question'");
    }
}