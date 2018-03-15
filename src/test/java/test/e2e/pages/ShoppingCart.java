package test.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCart extends BasePage {

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
    PageFactory.initElements(browser, this);
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
