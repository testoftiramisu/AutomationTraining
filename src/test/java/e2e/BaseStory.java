package e2e;

import com.google.common.util.concurrent.MoreExecutors;
import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

public class BaseStory extends JUnitStories {

    private static ChromeDriverService service;
    private static WebDriver browser;

    private static File CurrentPath = new File("");
    private static final File PROJECT_PATH = new File(CurrentPath.getAbsolutePath());
    private static final String CHROME_DRIVER_PATH = "/utils/2.27/chromedriver.exe";

    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
    private SeleniumContext context = new SeleniumContext();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);

    public static WebDriver getBrowser() {
        return browser;
    }

    public BaseStory() {
        if (lifecycleSteps instanceof PerStoriesWebDriverSteps) {
            configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
        }
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

    @BeforeStory
    public final void beforeStory() throws Exception {
        browser = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        browser.manage().window().maximize();
    }

    @AfterStory
    public final void afterStory() throws Exception {
        if (browser != null) {
            browser.quit();
        }
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration()
                .useSeleniumContext(context)
                .useWebDriverProvider(driverProvider)
                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(CONSOLE, TXT, HTML, XML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory(configuration,
                this,
                lifecycleSteps,
                new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story"), null);
    }

    public static class SameThreadEmbedder extends Embedder {
        public SameThreadEmbedder() {
            useExecutorService(MoreExecutors.sameThreadExecutor());
        }
    }
}

