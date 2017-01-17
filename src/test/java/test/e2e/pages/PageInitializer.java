package test.e2e.pages;

import org.openqa.selenium.WebDriver;

public class PageInitializer {

    private final WebDriver browser;

    public PageInitializer(WebDriver browser) {
        this.browser = browser;
    }

    public final DishDetails getDishesDetailsPage() {
        return new DishDetails(browser);
    }

    public final DishesSelection getDishesSelectionPage() {
        return new DishesSelection(browser);
    }

    public final Order getOrderPage() {
        return new Order(browser);
    }
}
