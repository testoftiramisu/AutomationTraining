package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DishesDetails extends BasePage {

    private String dishParameterValueXpath = ".//*[text()='%s']/following-sibling::dd[1]";

    @FindBy(xpath = ".//*[@id='description']/h1")
    private WebElement dishName;

    @FindBy(xpath = ".//*[@id='description']/p")
    private WebElement dishDescription;

    @FindBy(id = "price-quantity")
    private WebElement dishPrice;

    @FindBy(className = "buy")
    private WebElement addToCart;

    @FindBy(id = "navigate-prev")
    private WebElement previousDish;

    @FindBy(id = "navigate-next")
    private WebElement nextDish;

    public DishesDetails(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }


}
