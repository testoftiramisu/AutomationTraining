package test.e2e.stories;

public class CartActions extends BaseStory {

    /**
     * Constructs a new instance of the test.  The constructor requires three string parameters, which represent the operating
     * system, version and browser to be used when launching a Sauce VM.  The order of the parameters should be the same
     * as that of the elements within the {@link #browsersStrings()} method.
     *
     * @param os
     * @param version
     * @param browser
     * @param deviceName
     * @param deviceOrientation
     */
    public CartActions(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
        setStory("**/CartActions.story");
    }
}
