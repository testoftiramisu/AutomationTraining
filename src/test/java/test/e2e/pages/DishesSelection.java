package test.e2e.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PriceConverter;

import java.util.List;

public class DishesSelection extends BasePage {

    @FindBy(xpath = ".//strong")
    private List<WebElement> dishes;

    @FindBy(xpath = ".//button")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "selected-image-price")
    private List<WebElement> dishesInCart;

    @FindBy(xpath = ".//*[contains(@data-bind,'itemPrice')]")
    private List<WebElement> pricesInCart;

    @FindBy(xpath = ".//*[contains(@data-bind,'quantity')]")
    private List<WebElement> quantitiesInCart;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "empty-cart")
    private WebElement emptyCartButton;

    @FindBy(className = "total-price")
    private WebElement totalPrice;

    @FindBy(id = "shop-info")
    private WebElement cart;

    public DishesSelection(WebDriver browser) {
        super(browser);
        org.openqa.selenium.support.PageFactory.initElements(browser, this);
    }

    public void navigate(String url) {
        getBrowser().get(url);
    }

    public void viewDishDetails(String dishName) {
        dishes.stream().filter(dish -> dish.getText().equals(dishName)).forEach(WebElement::click);
    }

    public void addDishToCart(String dishName) {
        dishes.stream()
                .filter(dish -> dish.getText().equalsIgnoreCase(dishName))
                .forEach(dish -> dish.findElement(By.xpath("../..")).findElement(By.className("add-to-cart")).click());
    }

    public void addRandomDish() {
        try {
            addToCartButtons.get(0).click();
        } catch (NotFoundException elementIsNotFound) {
            System.out.println("There are no dishes on a page: " + elementIsNotFound);
        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < dishesInCart.size(); i++) {
            totalPrice += PriceConverter.getTotalPriceAsDouble(pricesInCart.get(i))
                    * PriceConverter.getTotalPriceAsDouble(quantitiesInCart.get(i));
        }
        return totalPrice;
    }

    public void checkout() {
        try {
            checkoutButton.click();
        } catch (ElementNotVisibleException ex) {
            System.out.println("Checkout button is not visible" + ex);
        }
    }

    public void emptyCart() {
        try {
            WebElement emptyCartButton = new WebDriverWait(getBrowser(), 10)
                    .until(ExpectedConditions.elementToBeClickable(By.id("empty-cart")));
            emptyCartButton.click();
        } catch (ElementNotVisibleException ex) {
            System.out.println("Empty Cart button is not visible" + ex);
        }
    }

    public double getTotalPrice() {
        return PriceConverter.getTotalPriceAsDouble(totalPrice);
    }

    public List<WebElement> getDishesInCart() {
        return dishesInCart;
    }

    public boolean isCartPresent() {
        return checkoutButton.isDisplayed();
    }

    public boolean isTotalPricePresent() {
        return totalPrice.isDisplayed();
    }


}
