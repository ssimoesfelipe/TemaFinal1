package com.felipesimoes1.cloud.tema08.operations;

public class Exponentiation implements Operation {

    @Override
    public double executeOperation(double firstNumber, double secondNumber) {
        return Math.pow(firstNumber,secondNumber);
    }
}

