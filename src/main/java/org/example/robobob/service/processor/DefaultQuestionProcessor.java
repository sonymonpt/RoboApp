package org.example.robobob.service.processor;

import org.example.robobob.util.QuestionConstants;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the QuestionProcessor interface.
 * This processor handles any question that does not match any specific processor.
 */
@Component
public class DefaultQuestionProcessor implements QuestionProcessor {

    @Override
    public boolean canProcess(String question) {
        return true; // Always returns true as it handles unknown questions
    }

    @Override
    public String process(String question) {
        return QuestionConstants.UNKNOWN_QUESTION_RESPONSE; // Default response for unknown questions
    }
}