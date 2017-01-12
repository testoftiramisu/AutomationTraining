package e2e.steps;

import e2e.pages.Page;
import utils.DataLoader;
import org.jbehave.core.annotations.Given;
import org.openqa.selenium.WebDriver;

/**
 * Created by Denys_Khlivnyy on 12.01.2017.
 */
public class BasicSteps extends Page {

    private String landingURL = DataLoader.getLandingURL();

    public BasicSteps(WebDriver browser) {
        super(browser);
    }

    @Given("I would like to eat Japanese food")
    public void login() {
        getBrowser().get(landingURL);
    }

}
