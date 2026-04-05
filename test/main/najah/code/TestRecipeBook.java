package main.najah.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
public class TestRecipeBook {

    private RecipeBook recipeBook;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: setup complete");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll: all tests finished");
    }

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();

        recipe1 = new Recipe();
        recipe1.setName("Espresso");

        recipe2 = new Recipe();
        recipe2.setName("Latte");

        System.out.println("BeforeEach: test starting");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: test finished");
    }

    @Test
    @DisplayName("Add a valid recipe successfully")
    void testAddRecipe_valid() {
        boolean added = recipeBook.addRecipe(recipe1);

        assertTrue(added);
        assertEquals("Espresso", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Adding duplicate recipe should fail")
    void testAddRecipe_duplicate() {
        recipeBook.addRecipe(recipe1);
        Recipe duplicate = new Recipe();
        duplicate.setName("Espresso");

        boolean added = recipeBook.addRecipe(duplicate);

        assertFalse(added);
        assertEquals("Espresso", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Adding recipe should fail when recipe book is full")
    void testAddRecipe_fullBook() {
        Recipe r1 = new Recipe(); r1.setName("R1");
        Recipe r2 = new Recipe(); r2.setName("R2");
        Recipe r3 = new Recipe(); r3.setName("R3");
        Recipe r4 = new Recipe(); r4.setName("R4");
        Recipe r5 = new Recipe(); r5.setName("R5");

        assertTrue(recipeBook.addRecipe(r1));
        assertTrue(recipeBook.addRecipe(r2));
        assertTrue(recipeBook.addRecipe(r3));
        assertTrue(recipeBook.addRecipe(r4));

        boolean added = recipeBook.addRecipe(r5);

        assertFalse(added);
        assertEquals("R4", recipeBook.getRecipes()[3].getName());
    }

    @Test
    @DisplayName("Delete existing recipe should return recipe name")
    void testDeleteRecipe_existing() {
        recipeBook.addRecipe(recipe1);

        String deletedName = recipeBook.deleteRecipe(0);

        assertEquals("Espresso", deletedName);
        assertEquals("", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Delete non-existing recipe should return null")
    void testDeleteRecipe_nonExisting() {
        String deletedName = recipeBook.deleteRecipe(0);

        assertNull(deletedName);
        assertNull(recipeBook.getRecipes()[0]);
    }

    @Test
    @DisplayName("Edit existing recipe should return old recipe name")
    void testEditRecipe_existing() {
        recipeBook.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Mocha");

        String editedName = recipeBook.editRecipe(0, newRecipe);

        assertEquals("Espresso", editedName);
        assertEquals("", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Edit non-existing recipe should return null")
    void testEditRecipe_nonExisting() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Mocha");

        String editedName = recipeBook.editRecipe(0, newRecipe);

        assertNull(editedName);
        assertNull(recipeBook.getRecipes()[0]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Americano", "Cappuccino", "Flat White"})
    @DisplayName("Parameterized test for adding recipes with different names")
    void testAddRecipe_parameterized(String recipeName) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);

        boolean added = recipeBook.addRecipe(recipe);

        assertTrue(added);
        assertEquals(recipeName, recipeBook.getRecipes()[0].getName());
    }

    @Test
    @Timeout(1)
    @DisplayName("Get recipes should finish quickly")
    void testGetRecipes_timeout() {
        Recipe[] recipes = recipeBook.getRecipes();

        assertNotNull(recipes);
        assertEquals(4, recipes.length);
    }

    @Test
    @DisplayName("Disabled failing test example")
    @org.junit.jupiter.api.Disabled("Intentionally disabled. Fix by changing expected value to match the actual recipe book size of 4.")
    void testDisabledFailingExample() {
        assertEquals(5, recipeBook.getRecipes().length);
    }
}