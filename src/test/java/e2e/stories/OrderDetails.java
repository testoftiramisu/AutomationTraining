package e2e.stories;

import org.jbehave.core.annotations.Given;
import utils.DataLoader;

public class OrderDetails extends BaseStory {

    private String landingURL = DataLoader.getLandingURL();

    public OrderDetails() {
        configuredEmbedder();
        setStory("**/OrderDetails.story");
    }

    @Given("I would like to eat Japanese food")
    public void login() {
        getBrowser().get(landingURL);
    }
}
