package org.example.robobob.service;

import org.example.robobob.service.processor.QuestionProcessor;
import org.example.robobob.service.processor.QuestionProcessorFactory;
import org.example.robobob.util.ArithmeticEvaluator;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {


    private final ArithmeticEvaluator evaluator;

    private final QuestionProcessorFactory processorFactory ;

    public QuestionService(ArithmeticEvaluator evaluator, QuestionProcessorFactory processorFactory) {
        this.evaluator = evaluator;
        this.processorFactory = processorFactory;
    }

    public String processQuestion(String question) {
        QuestionProcessor processor = processorFactory.getProcessor(question);
        return processor.process(question);
    }
}