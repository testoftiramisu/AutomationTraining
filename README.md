POC of Java based framework for E2E UI testing of http://demos.telerik.com/kendo-ui/websushi/ website.

This project is meant for educational purposes only. 

[![Travis Status](https://travis-ci.org/testoftiramisu/AutomationTraining.svg?branch=master)](https://travis-ci.org/testoftiramisu/AutomationTraining)

#### Dependencies

    selenium = "2.49.0"
    assertj = "3.6.1"
    jbehave = "4.0.5"
    junit = "4.11"
    jehaveWebSelenium = "3.5.5"

#### System requirements

* Java 8
* Gradle
* Chrome browser

### Environment Setup

1. Global Dependencies
    * Download [Gradle](https://gradle.org/gradle-download/)
    * or Install Gradle with [Homebrew](http://brew.sh/) (for MacOS)
    ```
    $ brew install gradle
    ```
2. Sauce Labs Credentials
    * In the terminal, export your Sauce Labs credentials as environmental variables:
    ```
    $ export SAUCE_USERNAME=<your Sauce Labs username>
    $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```
    
    * or add two new Environment Variables in Windows (restart required)

3. Local ChromeDriver configuration

    * Under 'utils' folder create a new directory, with name that match your ChromeDriver version (e.g. "2.27")

    * Download the latest version of ChromeDriver from: https://sites.google.com/a/chromium.org/chromedriver/downloads

    * Copy ChromeDriver executable to created folder.

    * Update *ChromeDriverVersion* in **test.properties** file:

            ChromeDriverVersion = 2.27
        
    * Update *createAndStartService()* method in **BaseStory.class** in order to use local ChromeDriver

#### Running

$ gradle clean e2e

or

$ gradle clean e2e --info

for observing the test output in console.

#### Resources
##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Junit Documentation](http://junit.org/javadoc/latest/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

*This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub.*
