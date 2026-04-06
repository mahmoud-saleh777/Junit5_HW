package main.najah.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRecipe {

    @Test
    @DisplayName("Default recipe values should be zero or empty")
    void testDefaultConstructor() {
        Recipe recipe = new Recipe();

        assertEquals("", recipe.getName());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Set valid recipe values")
    void testSetValidValues() throws RecipeException {
        Recipe recipe = new Recipe();

        recipe.setName("Mocha");
        recipe.setPrice("10");
        recipe.setAmtCoffee("2");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("3");

        assertEquals("Mocha", recipe.getName());
        assertEquals(10, recipe.getPrice());
        assertEquals(2, recipe.getAmtCoffee());
        assertEquals(1, recipe.getAmtMilk());
        assertEquals(1, recipe.getAmtSugar());
        assertEquals(3, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Set invalid numeric values should throw RecipeException")
    void testSetInvalidValues() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setPrice("-1"));
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-1"));
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("-1"));
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("-1"));
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("-1"));
    }

    @Test
    @DisplayName("Set non-numeric values should throw RecipeException")
    void testSetNonNumericValues() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setPrice("abc"));
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("abc"));
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("abc"));
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("abc"));
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("abc"));
    }

    @Test
    @DisplayName("Recipe equality should depend on name")
    void testEqualsAndHashCode() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();

        r1.setName("Latte");
        r2.setName("Latte");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertEquals("Latte", r1.toString());
    }

    @Test
    @DisplayName("Set name null should keep old value")
    void testSetNameNull() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");
        recipe.setName(null);

        assertEquals("Espresso", recipe.getName());
    }
}