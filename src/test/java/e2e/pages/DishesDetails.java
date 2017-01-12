package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Anatolii_Hanziuk on 1/12/2017.
 */
public class DishesDetails extends Page {

    private String dishParameterValueXpath = ".//*[text()='%s']/following-sibling::dd[1]";

    @FindBy(id = "description")
    private WebElement dishName;

    @FindBy(id = "price-quantity")
    private WebElement dishPrice;

    @FindBy(className = "buy")
    private WebElement addToCart;

    public DishesDetails(WebDriver browser) {
        super(browser);
    }


}
