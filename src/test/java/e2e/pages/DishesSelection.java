package e2e.pages;

import org.openqa.selenium.NotFoundException;
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

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "empty-cart")
    private WebElement emptyCartButton;

    @FindBy(className = "total-price")
    private WebElement totalPrice;

    @FindBy(className = "selected-image-price")
    private List<WebElement> dishesInCart;

    @FindBy(id = "shop-info")
    private WebElement cart;

    public DishesSelection(WebDriver browser) {
        super(browser);
    }

    public void selectDish(String dishName) {
        dishes.stream().filter(dish -> dish.getText().equals(dishName)).forEach(dish -> dish.click());
    }

    public void checkout() {
        checkoutButton.click();
    }

    public void emptyCart() {
        emptyCartButton.click();
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().replace("$",""));
    }

    public boolean isTotalPricePresent() {
        return totalPrice.isDisplayed();
    }

    public List<WebElement> getDishesInCart() {
        return dishesInCart;
    }

    public boolean isCartPresent() {
        return cart.isDisplayed();
    }

    public void addRandomDish() {
        try {
            addToCart.get(0).click();
        } catch (NotFoundException elementIsNotFound) {
            System.out.println("There are no dishes on a page: " + elementIsNotFound);
        }
    }

}
