package e2e.steps;

import e2e.stories.BaseStory;
import e2e.components.Dish;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

/**
 * Created by Anatolii_Hanziuk on 1/11/2017.
 */
public class DishesDetailsSteps extends BaseStory {

    @Given("next dishes are added to a cart: $dishesInfo")
    @When("I add next dishes to a cart: $dishesInfo")
    public void selectDishes(List<Dish> dishesInfo) {}

    @Given("I add $dishesQuantity dish to a cart")
    public void addRandomDishes(int dishesQunatity) {}

    @Given("I would like to view order details")
    public void checkout() {}

    @When("I would like to empty cart")
    public void emptyCart() {}

    @Then("total order price should be empty")
    public void isTotalOrderPriceEmpty() {}

    @Then("shopping cart should contain $dishesQuantity {dish|dishes}")
    public void verifyDishesQuantityInCart(int dishesQuantity) {}

    @Then("cart should be empty")
    public void isEmptyCart() {}
}
