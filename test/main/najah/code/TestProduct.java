package main.najah.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    @DisplayName("Create product with valid name and price")
    void testConstructor_valid() {
        Product product = new Product("Laptop", 1000);

        assertEquals("Laptop", product.getName());
        assertEquals(1000, product.getPrice());
        assertEquals(0, product.getDiscount());
    }

    @Test
    @DisplayName("Create product with negative price should throw exception")
    void testConstructor_invalidNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("Phone", -100);
        });
    }

    @Test
    @DisplayName("Apply valid discount")
    void testApplyDiscount_valid() {
        Product product = new Product("Mouse", 200);
        product.applyDiscount(25);

        assertEquals(25, product.getDiscount());
        assertEquals(150, product.getFinalPrice());
    }

    @Test
    @DisplayName("Apply negative discount should throw exception")
    void testApplyDiscount_negative() {
        Product product = new Product("Keyboard", 300);

        assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(-10);
        });
    }

    @Test
    @DisplayName("Apply discount greater than 50 should throw exception")
    void testApplyDiscount_aboveMaximum() {
        Product product = new Product("Monitor", 500);

        assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(60);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "100,10,90",
            "200,25,150",
            "500,50,250",
            "80,0,80"
    })
    @DisplayName("Final price should be calculated correctly")
    void testGetFinalPrice_parameterized(double price, double discount, double expectedFinalPrice) {
        Product product = new Product("Item", price);
        product.applyDiscount(discount);

        assertEquals(expectedFinalPrice, product.getFinalPrice(), 0.001);
    }

    @Test
    @Timeout(1)
    @DisplayName("Get final price should finish quickly")
    void testGetFinalPrice_timeout() {
        Product product = new Product("Tablet", 1000);
        product.applyDiscount(20);

        double finalPrice = product.getFinalPrice();

        assertEquals(800, finalPrice);
    }
}