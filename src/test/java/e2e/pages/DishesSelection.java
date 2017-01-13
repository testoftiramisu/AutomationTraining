package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

public class DishesSelection extends BasePage {

    @FindBy(xpath = ".//strong")
    private List<WebElement> dishes;

    @FindBy(xpath = ".//button")
    private List<WebElement> addToCart;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public DishesSelection(WebDriver browser) {
        super(browser);
        org.openqa.selenium.support.PageFactory.initElements(browser, this);
    }

    public void selectDish(String dishName) {
        dishes.stream().filter(dish -> dish.getText().equals(dishName)).forEach(dish -> dish.click());
    }

    public void addDishToCart(String dishName) {

        WebElement selectedDish = null;
        for (WebElement dish : dishes) {
            if (dish.getText().equalsIgnoreCase(dishName)) {
                selectedDish = dish;
            }
        }

        WebElement parentElement = selectedDish.findElement(By.xpath("../.."));
        WebElement addToCartButton = parentElement.findElement(By.className("add-to-cart"));
        addToCartButton.click();
    }

    public void checkout() {
        checkoutButton.click();
    }

}
