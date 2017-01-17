package test.e2e.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import test.e2e.pages.PageInitializer;
import test.e2e.stories.BaseStory;

public class DishesSelectionSteps {

    private PageInitializer pageFactory = new PageInitializer(BaseStory.getBrowser());

    @Given("next dishes are added to a cart: $dishesInfo")
    @When("I add next dishes to a cart: $dishesInfo")
    public void selectDishes(ExamplesTable dishesInfo) {
        dishesInfo.getRowsAsParameters()
                .stream()
                .forEach(row ->
                        pageFactory.getDishesSelectionPage()
                                .addDishToCart(row.valueAs("dish name", String.class)));
    }

    @Given("I add $dishesQuantity dish to a cart")
    public void addRandomDishes(int dishesQunatity) {
        for (int i = 0; i < dishesQunatity; i++) {
            pageFactory.getDishesSelectionPage().addRandomDish();
        }
    }

    @Given("I view order details")
    @When("I view order details")
    public void getOrderDetails() {
        pageFactory.getDishesSelectionPage().checkout();
    }

    @When("I would like to empty cart")
    public void emptyCart() {
        pageFactory.getDishesSelectionPage().emptyCart();
    }

    @Then("total order price should be empty")
    public void isTotalOrderPriceEmpty() {
        assertThat(pageFactory.getDishesSelectionPage().isTotalPricePresent()).isFalse();
    }

    @Then("shopping cart should contain $dishesQuantity {dishes|dish}")
    public void verifyDishesQuantityInCart(int dishesQuantity) {
        assertThat(pageFactory.getDishesSelectionPage().getDishesInCart().size())
                .isEqualTo(dishesQuantity);
    }

    @Then("cart should be empty")
    public void isEmptyCart() {
        assertThat(pageFactory.getDishesSelectionPage().isCartPresent())
                .isFalse();
    }

    @Then("total cart price should be equal to $orderTotalPrice")
    public void verifyTotalOrderPrice(String orderTotalPrice) {
        assertThat(Double.parseDouble(orderTotalPrice))
                .isEqualTo(pageFactory.getDishesSelectionPage().calculateTotalPrice());
    }
}
