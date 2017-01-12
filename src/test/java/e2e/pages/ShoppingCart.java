package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Anatolii_Hanziuk on 1/12/2017.
 */
public class ShoppingCart extends Page{

    @FindBy(id = "empty-cart")
    private WebElement emptyCartButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = ".//*[@id=\"shop-list\"]//a")
    private List<WebElement> removeFromCart;

    @FindBy(className = "total-price")
    private WebElement totalPrice;

    public ShoppingCart(WebDriver browser) {
        super(browser);
    }

    public void emptyCart() {
        emptyCartButton.click();
    }

    public void checkout() {
        checkoutButton.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }



}
