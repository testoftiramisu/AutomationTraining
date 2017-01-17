package test.e2e.steps;

import test.e2e.stories.BaseStory;
import utils.DataLoader;
import org.jbehave.core.annotations.Given;

public class AuthenticationSteps {

    private String landingURL = DataLoader.getLandingURL();

    @Given("I would like to eat Japanese food")
    public void login() {
        BaseStory.getBrowser().get(landingURL);
    }

}
