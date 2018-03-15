package utils;

import org.openqa.selenium.WebElement;

public class PriceConverter {

  public static double getTotalPriceAsDouble(WebElement totalPrice) {
    return Double.parseDouble(totalPrice.getText().replace("$", ""));
  }
}
