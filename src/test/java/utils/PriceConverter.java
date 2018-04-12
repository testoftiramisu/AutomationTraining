package utils;

import org.openqa.selenium.WebElement;

public class PriceConverter {

  /** Converts price from String value to double. */
  public static double getTotalPriceAsDouble(WebElement totalPrice) {
    return Double.parseDouble(totalPrice.getText().replace("$", ""));
  }
}
