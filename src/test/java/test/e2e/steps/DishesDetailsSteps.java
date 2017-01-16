package test.e2e.steps;

import test.e2e.pages.PageInitializer;
import test.e2e.stories.BaseStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;


/**
 * Created by Anatolii_Hanziuk on 1/11/2017.
 */
public class DishesDetailsSteps {

    private PageInitializer pageFactory = new PageInitializer(BaseStory.getBrowser());

    @Given("I add $dishesQuantity dish to a cart")
    public void addRandomDishes(int dishesQunatity) {}

    @When("I would like to empty cart")
    public void emptyCart() {}

    @Then("total order price should be empty")
    public void isTotalOrderPriceEmpty() {}

    @Then("shopping cart should contain $dishesQuantity {dish|dishes}")
    public void verifyDishesQuantityInCart(int dishesQuantity) {}

    @Then("cart should be empty")
    public void isEmptyCart() {}
}