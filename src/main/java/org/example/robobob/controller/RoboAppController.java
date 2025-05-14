package org.example.robobob.controller;

import org.example.robobob.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling questions and responses.
 */

@RestController
@RequestMapping("/api")
public class RoboAppController {

    private static final Logger logger = LoggerFactory.getLogger(RoboAppController.class);

    @Autowired
    QuestionService questionService;

    /**
     * Endpoint to ask a question.
     *
     * @param question The question to be asked.
     * @return The response from the service.
     */
    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody String question) {
        logger.info("Received question: {}", question);

        String response = questionService.processQuestion(question.trim());
        logger.debug("Processed response: {}", response);

        return ResponseEntity.ok(response);
    }
}