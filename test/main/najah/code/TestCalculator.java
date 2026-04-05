package main.najah.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculator {

    Calculator calc = new Calculator();

    @Test
    @DisplayName("Addition of two positive numbers")
    void testAdd_valid() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @DisplayName("Division by zero should throw exception")
    void testDivide_invalid() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(5, 0);
        });
    }

    @ParameterizedTest
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
    @DisplayName("Timeout test for addition")
    void testTimeout() {
        assertTimeout(java.time.Duration.ofSeconds(1), () -> {
            calc.add(10, 20);
        });
    }
}