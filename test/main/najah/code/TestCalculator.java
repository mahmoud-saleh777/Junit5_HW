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
    @DisplayName("Addition of two positive numbers")
    void testAdd_valid() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @Order(2)
    @DisplayName("Division by zero should throw exception")
    void testDivide_invalid() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(5, 0);
        });
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource({
            "1,2,3",
            "5,5,10",
            "-1,1,0"
    })
    @DisplayName("Parameterized addition test")
    void testAdd_parameterized(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    @Test
    @Order(4)
    @Timeout(1)
    @DisplayName("Timeout test for addition")
    void testTimeout() {
        assertEquals(30, calc.add(10, 20));
    }
}