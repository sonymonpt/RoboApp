package org.example.robobob.service.processor;

import org.example.robobob.util.ArithmeticEvaluator;
import org.springframework.stereotype.Component;

/**
 * This class processes arithmetic questions and evaluates them using the ArithmeticEvaluator.
 * It implements the QuestionProcessor interface.
 */
@Component
public class ArithmeticQuestionProcessor implements QuestionProcessor {
    private final ArithmeticEvaluator evaluator;

    // Constructor for dependency injection
    public ArithmeticQuestionProcessor(ArithmeticEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    /**
     * This method checks if the given expression is an arithmetic expression.
     * It uses the ArithmeticEvaluator to determine if the expression is valid.
     *
     * @param expression The expression to check.
     * @return true if the expression is an arithmetic expression, false otherwise.
     */
    public boolean canProcess(String expression) {
        return evaluator.isArithmeticExpression(expression);
    }

    /**
     * This method processes the arithmetic expression and evaluates it.
     * If the expression is valid, it returns the result as a string.
     *
     * @param expression The arithmetic expression to process.
     * @return The result of the evaluation as a string.
     * @throws IllegalArgumentException if the expression is invalid.
     */
    public String process(String expression) {
        if (evaluator.isArithmeticExpression(expression)) {

            return String.valueOf(evaluator.evaluate(expression));

        }
        throw new IllegalArgumentException("Invalid expression");
    }
}