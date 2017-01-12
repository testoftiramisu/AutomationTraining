package e2e.stories;

import com.google.common.util.concurrent.MoreExecutors;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
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
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.DataLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.jbehave.core.reporters.Format.*;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

public class BaseStory extends JUnitStories {

    private static ChromeDriverService service;
    private static WebDriver browser;
    private String storyPath;

    private static File CurrentPath = new File("");
    private static final File PROJECT_PATH = new File(CurrentPath.getAbsolutePath());
    private static final String SELENIUM_VERSION = DataLoader.getWebDriverVersion();
    private static final String CHROME_DRIVER_PATH = "/utils/" + SELENIUM_VERSION + "/chromedriver.exe";


    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);

    private PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
    private SeleniumContext context = new SeleniumContext();
    private SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView, context, new SilentStepMonitor());

    private CrossReference crossReference = new CrossReference()
            .withJsonOnly()
            .withPendingStepStrategy(pendingStepStrategy)
            .withOutputAfterEachStory(true)
            .excludingStoriesWithNoExecutedScenarios(true);

    private Format[] formats = new Format[]{new SeleniumContextOutput(context), CONSOLE, WEB_DRIVER_HTML};
    private StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(CodeLocations.codeLocationFromClass(BaseStory.class))
            .withFailureTrace(true)
            .withFailureTraceCompression(true).withDefaultFormats().withFormats(formats)
            .withCrossReference(crossReference);


    public static WebDriver getBrowser() {
        return browser;
    }

    public BaseStory() {
        if (lifecycleSteps instanceof PerStoriesWebDriverSteps) {
            configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
        }
    }

    @BeforeStory
    public final void beforeStory() throws Exception {
        browser = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        browser.manage().window().maximize();
    }

    @BeforeClass
    public static void createAndStartService() {
        try {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(PROJECT_PATH + CHROME_DRIVER_PATH))
                    .usingAnyFreePort()
                    .build();
            service.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    @AfterStory
    public final void afterStory() throws Exception {
        if (browser != null) {
            browser.quit();
        }
    }

    @Override
    public final List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources/"), storyPath, "");
    }

    @Override
    public final Configuration configuration() {
        return new SeleniumConfiguration().useSeleniumContext(context)
                .usePendingStepStrategy(pendingStepStrategy)
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false))
                .useStepMonitor(stepMonitor)
                .useStoryLoader(new LoadFromClasspath(BaseStory.class))
                .useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public final List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
    }

    public final void setStory(String story) {
        this.storyPath = story;
    }
}

