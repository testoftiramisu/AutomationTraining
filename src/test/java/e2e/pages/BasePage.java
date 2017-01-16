package e2e.pages;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {

    private WebDriver browser;

    public final WebDriver getBrowser() {
        return browser;
    }

    public BasePage(WebDriver browser) {
        this.browser = browser;
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public final void pause() {
        try {
            long timeoutInMilliseconds = 1000;
            Thread.sleep(timeoutInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

