package calculator.tests;

import calculator.Calculator;
import calculator.CalculatorException;
import org.junit.After;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void start_SIMPLE() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/simple.txt");
        calc.start();
        assertEquals("1.0\r\n", output.toString());
    }

    @Test
    void start_PLUS() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/plus.txt");
        calc.start();
        assertEquals("20.0\r\n", output.toString());
    }

    @Test
    void start_MINUS() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/minus");
        calc.start();
        assertEquals("0.0\r\n", output.toString());
    }

    @Test
    void start_MULT() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/mult");
        calc.start();
        assertEquals("100.0\r\n", output.toString());
    }

    @Test
    void start_DIV() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/div");
        calc.start();
        assertEquals("1.0\r\n", output.toString());
    }

    @Test
    void start_SQRT() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/sqrt");
        calc.start();
        assertEquals("10.0\r\n", output.toString());
    }

//    @Test(expected = CalculatorException.class)
//    void start_NOT_ENOUGH_ARGS() {
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(output));
//
//        Calculator calc = new Calculator("src/calculator/tests/files/simple.txt");
//        calc.start();
//        assertEquals("1.0\r\n", output.toString());
//    }

    @Test
    void start_MANY() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/multipleOperations");
        calc.start();
        assertEquals("-7.0\r\n", output.toString());
    }

    @Test
    void start_EXAMPLE() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Calculator calc = new Calculator("src/calculator/tests/files/taskExample");
        calc.start();
        assertEquals("2.0\r\n", output.toString());
    }

//    @Test
//    void start_NO_FILE() {
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(output));
//
//        Calculator calc = new Calculator("src/calculator/tests/files/error.txt");
//        assertEquals("The file is not open!\r\n", output.toString());
//
//    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}