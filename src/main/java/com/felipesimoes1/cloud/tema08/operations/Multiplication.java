package com.felipesimoes1.cloud.tema08.operations;

public class Multiplication implements Operation {

    @Override
    public double executeOperation(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }
}
