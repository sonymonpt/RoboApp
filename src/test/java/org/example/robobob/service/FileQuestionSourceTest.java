package org.example.robobob.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileQuestionSourceTest {

    @Test
    void testGetQuestions() {
        // Arrange
        FileQuestionSource fileQuestionSource = new FileQuestionSource();

        // Act
        Map<String, String> questions = fileQuestionSource.getQuestions();

        // Assert
        assertNotNull(questions, "Questions map should not be null");
        assertFalse(questions.isEmpty(), "Questions map should not be empty");
    }

}