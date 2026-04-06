package main.najah.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCalculator {

    Calculator calc = new Calculator();

    @Test
    @Order(1)
    @DisplayName("Addition positive numbers")
    void testAdd_valid() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @Order(2)
    @DisplayName("Addition with zero")
    void testAdd_zero() {
        assertEquals(5, calc.add(5, 0));
    }

    @Test
    @Order(3)
    @DisplayName("Addition negative numbers")
    void testAdd_negative() {
        assertEquals(-3, calc.add(-1, -2));
    }

    @Test
    @Order(4)
    @DisplayName("Addition mixed numbers")
    void testAdd_mixed() {
        assertEquals(1, calc.add(-2, 3));
    }

    @Test
    @Order(5)
    @DisplayName("Addition zero plus zero")
    void testAdd_zero_zero() {
        assertEquals(0, calc.add(0, 0));
    }

    @ParameterizedTest
    @Order(6)
    @CsvSource({
            "1,2,3",
            "5,5,10",
            "-1,1,0",
            "10,20,30"
    })
    @DisplayName("Parameterized addition test")
    void testAdd_parameterized(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    @Test
    @Order(7)
    @DisplayName("Division normal")
    void testDivide_valid() {
        assertEquals(2, calc.divide(6, 3));
    }

    @Test
    @Order(8)
    @DisplayName("Division by zero should throw exception")
    void testDivide_byZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    @Order(9)
    @DisplayName("Division negative numbers")
    void testDivide_negative() {
        assertEquals(-2, calc.divide(-6, 3));
    }

    @Test
    @Order(10)
    @DisplayName("Division negative by negative")
    void testDivide_negative_negative() {
        assertEquals(2, calc.divide(-6, -3));
    }

    @Test
    @Order(11)
    @DisplayName("Division zero numerator")
    void testDivide_zero_numerator() {
        assertEquals(0, calc.divide(0, 5));
    }

    @Test
    @Order(12)
    @DisplayName("Factorial of zero")
    void testFactorial_zero() {
        assertEquals(1, calc.factorial(0));
    }

    @Test
    @Order(13)
    @DisplayName("Factorial of positive number")
    void testFactorial_positive() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    @Order(14)
    @DisplayName("Factorial of one")
    void testFactorial_one() {
        assertEquals(1, calc.factorial(1));
    }

    @Test
    @Order(15)
    @DisplayName("Factorial negative input should throw exception")
    void testFactorial_negative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

    @Test
    @Order(16)
    @Timeout(1)
    @DisplayName("Timeout test for calculator operations")
    void testTimeout() {
        assertEquals(30, calc.add(10, 20));
        assertEquals(6, calc.factorial(3));
    }
}