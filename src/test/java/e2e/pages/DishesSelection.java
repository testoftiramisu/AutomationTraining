package e2e.pages;

import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public DishesSelection(WebDriver browser) {
        super(browser);
    }

    public void selectDish(String dishName) {
        dishes.stream().filter(dish -> dish.getText().equals(dishName)).forEach(dish -> dish.click());
    }

}
