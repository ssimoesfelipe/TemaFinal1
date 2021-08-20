package com.felipesimoes1.cloud.tema08.service;

import com.felipesimoes1.cloud.tema08.operations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    @Autowired
    ApplicationContext applicationContext;

    private static final List<String> operationsArray = new ArrayList<>();

    public List<String> getHistory() {
        return new ArrayList<>(operationsArray);
    }

    public String doOperation(double firstNumber, String op, double secondNumber) {
        Operation operation = (Operation) applicationContext.getBean(op, firstNumber, secondNumber);
        String resultString = "Result of " + op.toLowerCase() + " between " + firstNumber + " and " + secondNumber + " is " +
                operation.executeOperation(firstNumber,secondNumber);
        operationsArray.add(resultString);
        return resultString;
    }
}
