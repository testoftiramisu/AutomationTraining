POC of Java based framework for E2E UI testing of http://demos.telerik.com/kendo-ui/websushi/ website.

This project is meant for educational purposes only. 

[![Travis Status](https://travis-ci.org/testoftiramisu/AutomationTraining.svg?branch=master)](https://travis-ci.org/testoftiramisu/AutomationTraining)
[![Build Status](https://saucelabs.com/buildstatus/testoftiramisu)](https://saucelabs.com/beta/dashboard/builds)

[![Build Status](https://saucelabs.com/browser-matrix/testoftiramisu.svg)](https://saucelabs.com/beta/builds/44741d9dea14443f84cd7151be2f7f58)

#### Dependencies

    selenium = "2.49.0"
    assertj = "3.6.1"
    jbehave = "4.0.5"
    junit = "4.11"
    jehaveWebSelenium = "3.5.5"
    sauceRest = "1.0.35"

#### System requirements

* Java 8
* Gradle
* Chrome browser

### Environment Setup

1. Make sure Java 8 SDK is installed and configured (path to Java bin folder is added to PATH environment variable on Windows)

2. Install Gradle:
    * Download [Gradle](https://gradle.org/gradle-download/) and follow installation instructions. For MacOS users: install Gradle with [Homebrew](http://brew.sh/):
        ```
        $ brew install gradle
        ``` 
2. Sauce Labs Credentials: 

    Use your sauce credentials or register a new user on [saucelabs.com](https://saucelabs.com/).
    Add sauce credentials to environment variables:
   
    * For Windows: add two new Environment Variables (restart required)

    * For *nix: in terminal, export your Sauce Labs credentials as environmental variables:
        ```
        $ export SAUCE_USERNAME=<your Sauce Labs username>
        $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
        ```

3. In order to use local ChromeDriver, use next configuration:

    * Download the latest version of ChromeDriver from: https://sites.google.com/a/chromium.org/chromedriver/downloads
    
    * Under 'utils' folder create a new directory, with name that matches your ChromeDriver version (e.g. "2.27")

    * Copy ChromeDriver executable to created folder.

    * Update *ChromeDriverVersion* in **test.properties** file:

            ChromeDriverVersion = 2.27
        
    * Set property *localRun* in **test.properties** file to *true*: 
    
            localRun = false

#### Running

* In terminal run Gradle from project root:
       
    * Use e2e target for running all tests:
    
        ```
        $ gradle clean e2e
        ```

    * you can use --info key for observing the test output in console:

        ```
        $ gradle clean e2e --info
        ```

    

#### Resources

##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Junit Documentation](http://junit.org/javadoc/latest/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

##### [jBehave Documentation](http://jbehave.org/reference/stable/)

##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

*This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub.*
