package test.e2e.stories;

public class CartActions extends BaseStory {

    /**
     * Constructs a new instance of the test.
     * The constructor requires three string parameters, which represent the operating
     * system, version and browser to be used when launching a Sauce VM.
     * The order of the parameters should be the same as that of the elements within the
     * {@link #browsersStrings()} method.
     *
     * @param os          Desired OS.
     * @param version     Version of the desired OS.
     * @param browser     Desired browser.
     * @param name        Device name.
     * @param orientation Device orientation.
     */
    public CartActions(String os, String version, String browser, String name, String orientation) {
        super(os, version, browser, name, orientation);
        setStory("**/CartActions.story");
    }
}
