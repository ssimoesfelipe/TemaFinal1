package com.felipesimoes1.cloud.tema08;

import com.felipesimoes1.cloud.tema08.configuration.AppConfig;
import com.felipesimoes1.cloud.tema08.operations.Operation;
import com.felipesimoes1.cloud.tema08.service.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.felipesimoes1.cloud.tema08")
public class CalculatorTest {

    @Test
    public void shouldPerformAddition() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator additionTest = (Calculator) context.getBean("calculator");
        assertEquals("Result of addition between 2.0 and 2.0 is 4.0", additionTest.doOperation(2, "addition",2));
    }

    @Test
    public void shouldPerformSubstraction() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator subtractionTest = (Calculator) context.getBean("calculator");
        assertEquals("Result of subtraction between 2.0 and 2.0 is 0.0", subtractionTest.doOperation(2, "subtraction",2));
    }

    @Test
    public void shouldPerformMultiplication() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator multiplicationTest = (Calculator) context.getBean("calculator");
        assertEquals("Result of multiplication between 5.0 and 5.0 is 25.0", multiplicationTest.doOperation(5, "multiplication",5));
    }

    @Test
    public void shouldPerformDivision() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator divisionTest = (Calculator) context.getBean("calculator");
        assertEquals("Result of division between 10.0 and 2.0 is 5.0", divisionTest.doOperation(10, "division",2));
    }

    @Test
    public void shouldGiveExceptionWhenDividerIsZero() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator divisionTest = (Calculator) context.getBean("calculator");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> divisionTest.doOperation(10, "division",0));
        assertEquals("Divider cannot be zero.", e.getMessage());
    }

    @Test
    public void shouldPerformExponentialExpression() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator exponentiationTest = (Calculator) context.getBean("calculator");
        assertEquals("Result of exponentiation between 3.0 and 2.0 is 9.0", exponentiationTest.doOperation(3, "exponentiation",2));
    }
}