package main.najah.code;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestCalculator.class,
        TestProduct.class,
        TestUserService.class,
        TestRecipeBook.class,
        TestRecipe.class
})
public class TestSuite {
}