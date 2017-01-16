package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PriceConverter;

import java.util.List;

public class Order extends BasePage {

    @FindBy(className = "product-name")
    private List<WebElement> dishNames;

    @FindBy(xpath = "//*[@id=\"details-checkout\"]//*[@class=\"k-formatted-value k-input\"]")
    private List<WebElement> dishQuantities;

    @FindBy(className = "table-price")
    private List<WebElement> dishPrices;

    @FindBy(id = "total-checkout")
    private WebElement totalPrice;

    @FindBy(className = "cancel-order")
    private WebElement cancelOrderButton;

    @FindBy(className = "order-now")
    private WebElement orderNowButton;

    public Order(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public void cancelOrder() {
        cancelOrderButton.click();
    }

    public void orderNow() {
        orderNowButton.click();
    }

    public double calculateTotalOrderPrice() {
        return dishPrices.stream().mapToDouble(PriceConverter::getTotalPriceAsDouble).sum();
    }

    public boolean dishIsPresentInOrder(String dishName) {
        return dishNames.stream().filter(dish -> dish.getText().equalsIgnoreCase(dishName)).count() == 1;
    }

    public double getTotalOrderPrice() {
        return PriceConverter.getTotalPriceAsDouble(totalPrice);
    }

    public double getDishPrice(String dishName) {
        return PriceConverter.getTotalPriceAsDouble((WebElement) dishPrices.get(getDishIndexByDishName(dishName)));
    }

    public double getDishQuantity(String dishName) {
        return Double.parseDouble(dishQuantities.get(getDishIndexByDishName(dishName)).getAttribute("title"));
    }

    public void setDishQuantity(int quantity, String dishName) {
        /*dishQuantities.get(getDishIndexByDishName(dishName)).getAttribute("title")
                .replace(Double.toString(getDishQuantity(dishName)), Integer.toString(quantity));*/
        dishQuantities.get(getDishIndexByDishName(dishName)).sendKeys(Integer.toString(quantity));
    }

    public int getQuantityOfDishes() {
        return dishQuantities.size();
    }

    private int getDishIndexByDishName(String dishName) {
        for (WebElement dish : dishNames) {
            if (dish.getText().equalsIgnoreCase(dishName)) {
                return dishNames.indexOf(dish);
            }
        }
        return -1;
    }
}

