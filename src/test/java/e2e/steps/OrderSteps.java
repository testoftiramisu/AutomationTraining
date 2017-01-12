package e2e.steps;

import e2e.components.Dish;
import e2e.pages.Page;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by Anatolii_Hanziuk on 1/11/2017.
 */
public class OrderSteps extends Page{

    public OrderSteps(WebDriver browser) {
        super(browser);
    }

    @When("I view order details")
    public void getOrderDetails() {}

    @When("I change quantity of $dishName to $dishQuantity")
    public void setDishQuantity(String dishName, int dishQuantity) {}

    @When("I cancel an order")
    public void cancelOrder() {}

    @Then("cart should contain $dishesQuantity dishes")
    public void verifyCartSize(int dishesQuantity) {}

    @Then("total order price should be equal to $orderTotalPrice")
    public void verifyTotalOrderPrice(int orderTotalPrice) {}

    @Then("next dishes should be included to an order: $dishesInfo")
    public void verifyDishesInOrder(List<Dish> dishesInfo) {}
}
