package e2e.steps;

import e2e.pages.PageInitializer;
import e2e.stories.BaseStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import org.openqa.selenium.WebDriver;

public class DishesSelectionSteps {

    private WebDriver browser = BaseStory.getBrowser();
    private PageInitializer pageFactory = new PageInitializer(browser);

    @Given("next dishes are added to a cart: $dishesInfo")
    @When("I add next dishes to a cart: $dishesInfo")
    public void selectDishes(ExamplesTable dishesInfo) {
        for (Parameters row : dishesInfo.getRowsAsParameters()) {
            String dish = row.valueAs("dish name", String.class);
            pageFactory.getDishesSelectionPage().addDishToCart(dish);
        }
    }

    @Given("I add $dishesQuantity dish to a cart")
    public void addRandomDishes(int dishesQuantity) {
    }

    @Given("I would like to view order details")
    public void checkout() {
    }

    @When("I would like to empty cart")
    public void emptyCart() {
    }

    @Then("total order price should be empty")
    public void isTotalOrderPriceEmpty() {
    }

    @Then("shopping cart should contain $dishesQuantity {dish|dishes}")
    public void verifyDishesQuantityInCart(int dishesQuantity) {
    }

    @Then("cart should be empty")
    public void isEmptyCart() {
    }
}
