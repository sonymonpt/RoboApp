package org.example.robobob.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * FileQuestionSource is a concrete implementation of the QuestionSource interface.
 * It loads questions from a properties file located in the classpath.
 */
@Component
public class FileQuestionSource implements QuestionSource {

    @Override
    public Map<String, String> getQuestions() {
        Map<String, String> questions = new HashMap<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("questions.properties")) {
            if (input == null) {
                throw new IllegalStateException("questions.properties file not found");
            }
            Properties properties = new Properties();
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                questions.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load questions from file", e);
        }
        return questions;
    }
}