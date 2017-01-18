package test.e2e.steps;

import org.jbehave.core.annotations.Given;
import test.e2e.stories.BaseStory;
import utils.DataLoader;

public class AuthenticationSteps {

    private String landingUrl = DataLoader.getLandingURL();

    @Given("I would like to eat Japanese food")
    public void login() {
        BaseStory.getBrowser().get(landingUrl);
    }

}
