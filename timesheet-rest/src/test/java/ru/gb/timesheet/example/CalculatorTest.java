package ru.gb.timesheet.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {


    // JUnit (4), JUnit5 = фреймворк для тестирования в Java
    // Mockito - библиотека для моков
    // AssertJ - библиотека для удобных асертов

    @Test
    void testSum(){
        Calculator calculator = new Calculator();
        int actual = calculator.sum(2,3);
        int expected = 5;

        Assertions.assertEquals(expected, actual);

    }


}
