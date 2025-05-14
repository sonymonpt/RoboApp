package org.example.robobob.service.processor;

import org.example.robobob.service.QuestionSource;
import org.example.robobob.util.ArithmeticEvaluator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Factory class to create instances of QuestionProcessor.
 * It provides a method to get the appropriate processor based on the question type.
 */
@Component
public class QuestionProcessorFactory {

    private final List<QuestionProcessor> processors;
    private final QuestionProcessor defaultProcessor;

    /**
     * Constructor to initialize the factory with a list of processors and a default processor.
     *
     * @param evaluator      The ArithmeticEvaluator to be used by the ArithmeticQuestionProcessor.
     * @param questionSource The source of questions.
     */
    public QuestionProcessorFactory(ArithmeticEvaluator evaluator, QuestionSource questionSource) {
        this.processors = List.of(
                new BasicQuestionProcessor(questionSource),
                new ArithmeticQuestionProcessor(evaluator) // Inject evaluator
        );
        this.defaultProcessor = new DefaultQuestionProcessor();// Initialize default processor
    }

    /**
     * Method to get the appropriate processor for a given question.
     *
     * @param question The question to be processed.
     * @return The appropriate QuestionProcessor instance.
     */
    public QuestionProcessor getProcessor(String question) {
        return processors.stream()
                .filter(processor -> processor.canProcess(question))
                .findFirst()
                .orElse(defaultProcessor); // Return default processor if no match is found
    }
}