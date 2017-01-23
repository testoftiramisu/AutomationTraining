package test.e2e.stories;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

import com.saucelabs.saucerest.SauceREST;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.e2e.steps.DishesSelectionSteps;
import test.e2e.steps.OrderSteps;

import utils.DataLoader;
import utils.StoryPathConverter;

@RunWith(Parameterized.class)
public class BaseStory extends JUnitStories {
    private static final String SELENIUM_VERSION = DataLoader.getWebDriverVersion();
    private static final File CurrentPath = new File("");
    private static final File PROJECT_PATH = new File(CurrentPath.getAbsolutePath());
    private static final boolean RUN_LOCALLY = DataLoader.isLocalRun();

    private static final String USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    private static final SauceREST sauceClient = new SauceREST(USERNAME, ACCESS_KEY);
    private static final List<String> storiesNames = StoryPathConverter
            .convertStringToListOfStoryPaths(DataLoader.getStoriesToRun());

    private static WebDriver driver;
    private static String CHROME_DRIVER_PATH = "/utils/" + SELENIUM_VERSION + "/chromedriver";
    private static String buildTag;

    private String browser;
    private String os;
    private String version;
    private String deviceName;
    private String deviceOrientation;

    public String sessionId;

    static {
        if (System.getProperty("os.name").startsWith("Windows")) {
            CHROME_DRIVER_PATH += ".exe";
        }
    }

    private PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
    private CrossReference crossReference = new CrossReference().withJsonOnly();
    private SeleniumContext seleniumContext = new SeleniumContext();

    private Format[] formats = new Format[]{
            new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML
    };

    private StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(CodeLocations.codeLocationFromClass(BaseStory.class))
            .withFailureTrace(true)
            .withFailureTraceCompression(true)
            .withDefaultFormats()
            .withFormats(formats)
            .withCrossReference(crossReference);

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };


    /**
     * Constructs a new instance of the test.
     * Constructor requires three string parameters, which represent the operating system, version
     * and browser to be used when launching a Sauce VM.
     * The order of the parameters should be the same as that of the elements within the
     * {@link #browsersStrings()} method.
     */
    public BaseStory(String os, String version, String browser, String device, String orientation) {

        EmbedderControls embedderControls = configuredEmbedder().embedderControls();
        embedderControls.doIgnoreFailureInView(true);
        configuredEmbedder().embedderControls();

        this.os = os;
        this.version = version;
        this.browser = browser;
        this.deviceName = device;
        this.deviceOrientation = orientation;
    }

    /**
     * Representing the browser combinations the test should be run against.
     * The values in the String array are used as part of the invocation of the test constructor.
     *
     * @return a LinkedList containing String arrays,
     */
    @Parameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList<String[]> browsers = new LinkedList<>();

        if (!RUN_LOCALLY) {
            browsers.add(new String[]{"Windows 10", "49.0", "firefox", null, null});
            //   browsers.add(new String[]{"Windows 7", "11.0", "internet explorer", null, null});
            browsers.add(new String[]{"OS X 10.11", "10.0", "safari", null, null});
            browsers.add(new String[]{"OS X 10.10", "54.0", "chrome", null, null});
        } else {
            browsers.add(new String[]{null, null, null, null, null});
        }
        return browsers;
    }

    /**
     * Perform setup of local ChromeDriver in case of running Test Suite locally.
     */
    @BeforeClass
    public static void createAndStartService() {
        buildTag = System.getenv("BUILD_TAG");

        if (RUN_LOCALLY) {
            startLocalChromeDriver();
        }
    }

    private void startSauceLabsWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("device-orientation", deviceOrientation);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", os + ": " + browser + " " + DataLoader.getStoriesToRun());

        // Getting the build name.
        // Using the Jenkins ENV var. You can use your own.
        // If it is not set test will run without a build id.
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        try {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

        driver.manage().window().maximize();
    }

    private static void startLocalChromeDriver() {
        ChromeDriverService service;
        try {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(PROJECT_PATH + CHROME_DRIVER_PATH))
                    .usingAnyFreePort()
                    .build();
            service.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
    }

    public static WebDriver getDriver() {
        return driver;
    }


    /**
     * Initial environment SetUp in case of running test suite on SauceLabs.
     */
    @Before
    public final void setUp() {
        if (!RUN_LOCALLY) {
            startSauceLabsWebDriver();
        }
    }

    /**
     * Perform clean of target/jbehave directory before test run.
     */
    @Before
    public final void clean() throws IOException {
        try {
            File dir = new File("target/jbehave");
            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Web browser should be closed after run of every story.
     */
    @After
    public void afterStory() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public final List<String> storyPaths() {
        String codeLocation = "src/test/resources/";
        return new StoryFinder()
                .findPaths(codeLocation, storiesNames, null);
    }

    @Override
    public final Configuration configuration() {
        return new SeleniumConfiguration().useSeleniumContext(seleniumContext)
                .usePendingStepStrategy(pendingStepStrategy)
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStoryLoader(new LoadFromClasspath(BaseStory.class))
                .useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new DishesSelectionSteps(),
                new OrderSteps()
        );
    }

    /**
     * In case you need value of SauceLabs job id, this method will
     *
     * @return the value of the Sauce Job id.
     */
    public String getSessionId() {
        return sessionId;
    }

    @Rule
    public TestRule watcher = new TestWatcher() {

        @Override
        protected void failed(Throwable throwable, Description description) {
            sauceClient.jobFailed(sessionId);
            System.out.println(String.format("https://saucelabs.com/tests/%s", sessionId));
        }

        @Override
        protected void succeeded(Description description) {
            sauceClient.jobPassed(sessionId);
        }
    };
}

