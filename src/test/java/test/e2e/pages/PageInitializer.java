package test.e2e.pages;

import org.openqa.selenium.WebDriver;
import test.e2e.stories.BaseStory;

public class PageInitializer {

  private WebDriver driver;

  private void setDriver() {
    if (driver == null) {
      this.driver = BaseStory.getDriver();
    }
  }

  public final DishDetails getDishesDetailsPage() {
    setDriver();
    return new DishDetails(driver);
  }

  public final DishesSelection getDishesSelectionPage() {
    setDriver();
    return new DishesSelection(driver);
  }

  public final Order getOrderPage() {
    setDriver();
    return new Order(driver);
  }
}