package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Anatolii_Hanziuk on 1/12/2017.
 */
public class DishesSelection extends Page {

    @FindBy(xpath = ".//strong")
    private List<WebElement> dishes;

    @FindBy(xpath = ".//button")
    private List<WebElement> addToCart;

    @FindBy(id = "empty-cart")
    private WebElement emptyCart;

    @FindBy(id = "checkout")
    private WebElement checkout;

    @FindBy(xpath = ".//*[@id=\"shop-list\"]//a")
    private List<WebElement> removeFromCart;

    @FindBy(className = "total-price")
    private WebElement totalPrice;

    public DishesSelection emptyCart() {
        emptyCart.click();
        return this;
    }

    public void checkout() {
        checkout.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void selectDish(String dishName) {
        dishes.stream().filter(dish -> dish.getText().equals(dishName)).forEach(dish -> dish.click());
    }

    public DishesSelection(WebDriver browser) {
        super(browser);
    }
}
