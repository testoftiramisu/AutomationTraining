package e2e.steps;

import e2e.stories.BaseStory;
import utils.DataLoader;
import org.jbehave.core.annotations.Given;

public class BasicSteps {

    private String landingURL = DataLoader.getLandingURL();

    @Given("I would like to eat Japanese food")
    public void login() {
        BaseStory.getBrowser().get(landingURL);
    }

}
