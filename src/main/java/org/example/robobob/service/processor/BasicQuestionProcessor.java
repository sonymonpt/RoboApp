package org.example.robobob.service.processor;

import org.example.robobob.service.QuestionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * BasicQuestionProcessor is responsible for processing basic questions.
 * It checks if the question can be processed and returns the appropriate response.
 */
@Component
public class BasicQuestionProcessor implements QuestionProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BasicQuestionProcessor.class);

    private final QuestionSource questionSource;

    public BasicQuestionProcessor(QuestionSource questionSource) {
        this.questionSource = questionSource;
    }

    /**
     * Checks if the question can be processed by this processor.
     *
     * @param question The question to check.
     * @return true if the question can be processed, false otherwise.
     */
    @Override
    public boolean canProcess(String question) {
        boolean canProcess = questionSource.getQuestions().containsKey(question);
        logger.debug("Can process '{}': {}", question, canProcess);
        return canProcess;
    }

    /**
     * Processes the question and returns the response.
     *
     * @param question The question to process.
     * @return The response to the question.
     */
    @Override
    public String process(String question) {
        String response = questionSource.getQuestions().getOrDefault(question, "I don't understand the question.");
        logger.info("Processing question: '{}', Response: '{}'", question, response);
        return response;
    }
}