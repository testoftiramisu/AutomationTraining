package e2e.pages;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Page {
    private WebDriver browser;

    public final WebDriver getBrowser() {
        return browser;
    }

    public Page(WebDriver browser) {
        this.browser = browser;
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pause();
    }

    public final void pause() {
        try {
            long timeoutInMilliseconds = 3000;
            Thread.sleep(timeoutInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

