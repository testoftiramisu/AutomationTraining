package utils;

import org.openqa.selenium.WebElement;

/**
 * Created by Anatolii_Hanziuk on 1/13/2017.
 */
public class PriceConverter {

    public static double getTotalPriceAsDouble(WebElement totalPrice ) {
        return Double.parseDouble(totalPrice.getText().replace("$",""));
    }
}
