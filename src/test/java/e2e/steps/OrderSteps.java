package e2e.steps;

import e2e.stories.BaseStory;
import e2e.components.Dish;
import e2e.pages.PageFactory;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anatolii_Hanziuk on 1/11/2017.
 */
public class OrderSteps {

    private WebDriver browser = BaseStory.getBrowser();
    private PageFactory pageFactory = new PageFactory(browser);

    @When("I change quantity of $dishName to $dishQuantity")
    public void setDishQuantity(String dishName, String dishQuantity) {
        pageFactory.getOrderPage().setDishQuantity(dishName, dishQuantity);
    }

    @When("I cancel an order")
    public void cancelOrder() {
        pageFactory.getOrderPage().cancelOrder();
    }

    @Then("cart should contain $dishesQuantity dishes")
    public void verifyCartSize(int dishesQuantity) {
        assertThat(dishesQuantity).isEqualTo(pageFactory.getOrderPage().getQuantityOfDishes());
    }

    @Then("total order price should be equal to $orderTotalPrice")
    public void verifyTotalOrderPrice(String orderTotalPrice) {
        assertThat(Double.parseDouble(orderTotalPrice)).isEqualTo(pageFactory.getOrderPage().calculateTotalOrderPrice());
    }

    @Then("next dishes should be included to an order: $dishesInfo")
    public void verifyDishesInOrder(List<Dish> dishesInfo) {
    }
}
