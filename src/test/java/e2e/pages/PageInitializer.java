package e2e.pages;

import org.openqa.selenium.WebDriver;

public class PageInitializer {

    private final WebDriver browser;

    public PageInitializer(WebDriver browser) {
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
