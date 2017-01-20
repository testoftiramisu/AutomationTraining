package test.e2e.steps;

import test.e2e.pages.Order;
import test.e2e.pages.PageInitializer;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderSteps {

    private PageInitializer pageFactory = new PageInitializer();

    @When("I view order details")
    public void getOrderDetails() {
        pageFactory.getDishesSelectionPage().checkout();
    }

    @When("I change quantity of $dishName to $dishQuantity")
    public void setDishQuantity(String dishName, int dishQuantity) {
        pageFactory.getOrderPage().setDishQuantity(dishQuantity, dishName);
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
        assertThat(Double.parseDouble(orderTotalPrice))
                .isEqualTo(pageFactory.getOrderPage().calculateTotalOrderPrice());
    }

    @Then("next dishes should be included to an order: $dishesInfo")
    public void verifyDishesInOrder(ExamplesTable dishesInfo) {
        Order orderPage = pageFactory.getOrderPage();
        for (Parameters row : dishesInfo.getRowsAsParameters()) {
            String dishName = row.valueAs("item", String.class);
            Double dishQuantity = row.valueAs("quantity", Double.class);
            Double dishPrice = row.valueAs("price", Double.class);

            assertThat(orderPage.dishIsPresentInOrder(dishName)).isTrue();
            assertThat(orderPage.getDishPrice(dishName)).isEqualTo(dishPrice);
            assertThat(orderPage.getDishQuantity(dishName)).isEqualTo(dishQuantity);
        }
    }
}
