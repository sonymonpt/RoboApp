package org.example.robobob.util;

import org.springframework.stereotype.Component;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
/*
 * This class evaluates arithmetic expressions.
 * It checks if the input is a valid arithmetic expression and evaluates it.
 */
@Component
public class ArithmeticEvaluator {

    public boolean isArithmeticExpression(String input) {
        return input.matches("[0-9+\\-*/(). ]+");
    }

    public double evaluate(String expression) {
        Expression exp = new ExpressionBuilder(expression).build();
        return exp.evaluate();
    }
}