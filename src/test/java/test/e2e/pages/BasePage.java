package test.e2e.pages;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

class BasePage {

  private static final int TIMEOUT_IN_SECONDS = 5;
  private WebDriver browser;

  BasePage(WebDriver browser) {
    this.browser = browser;
    browser.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
  }

  final WebDriver getBrowser() {
    return browser;
  }
}
