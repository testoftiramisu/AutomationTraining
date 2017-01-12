package e2e.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Anatolii_Hanziuk on 1/12/2017.
 */
public class PageFactory {

    private final WebDriver browser;


    public PageFactory(WebDriver browser) {
        this.browser = browser;
    }

    public final DishesDetails getDishesDetailsPage() {
        return new DishesDetails(browser);
    }

    public final DishesSelection getDishesSelectionPage() {
        return new DishesSelection(browser);
    }

    public final Order getOrderPage() {
        return new Order(browser);
    }
}
