package com.felipesimoes1.cloud.tema08.operations;

public class Division implements Operation {

    @Override
    public double executeOperation(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new IllegalArgumentException("Divider cannot be zero.");
        }
        return firstNumber / secondNumber;
    }
}
