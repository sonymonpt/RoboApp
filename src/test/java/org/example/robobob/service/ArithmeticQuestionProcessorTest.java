package org.example.robobob.service;

import org.example.robobob.service.processor.ArithmeticQuestionProcessor;
import org.example.robobob.util.ArithmeticEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArithmeticQuestionProcessorTest {

    @Mock
    private ArithmeticEvaluator evaluator;

    private ArithmeticQuestionProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new ArithmeticQuestionProcessor(evaluator);
    }

    @Test
    void testCanProcessValidArithmeticExpression() {
        String expression = "2+2";
        when(evaluator.isArithmeticExpression(expression)).thenReturn(true);

        assertTrue(processor.canProcess(expression));
        verify(evaluator).isArithmeticExpression(expression);
    }

    @Test
    void testCanProcessInvalidArithmeticExpression() {
        String expression = "What is your name?";
        when(evaluator.isArithmeticExpression(expression)).thenReturn(false);

        assertFalse(processor.canProcess(expression));
        verify(evaluator).isArithmeticExpression(expression);
    }

    @Test
    void testProcessValidArithmeticExpression() throws ScriptException {
        String expression = "2+2";
        double result = 4.0;
        when(evaluator.isArithmeticExpression(expression)).thenReturn(true);
        when(evaluator.evaluate(expression)).thenReturn(result);

        assertEquals("4.0", processor.process(expression));
        verify(evaluator).isArithmeticExpression(expression);
        verify(evaluator).evaluate(expression);
    }

    @Test
    void testProcessInvalidArithmeticExpression() {
        String expression = "Invalid expression";
        when(evaluator.isArithmeticExpression(expression)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> processor.process(expression));
        assertEquals("Invalid expression", exception.getMessage());
        verify(evaluator).isArithmeticExpression(expression);
    }


}