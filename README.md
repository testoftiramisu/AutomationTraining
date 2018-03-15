POC of Java based framework for E2E UI testing of http://demos.telerik.com/kendo-ui/websushi/ website.

This project is meant for educational purposes only. 

[![Travis Status](https://travis-ci.org/testoftiramisu/AutomationTraining.svg?branch=master)](https://travis-ci.org/testoftiramisu/AutomationTraining)
[![Build Status](https://saucelabs.com/buildstatus/testoftiramisu)](https://saucelabs.com/beta/builds/3af96a86f6334729aadb86623abde508)

[![Build Status](https://saucelabs.com/browser-matrix/testoftiramisu.svg)](https://saucelabs.com/beta/builds/3af96a86f6334729aadb86623abde508)

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

2. Download dependencies:
    * Download [Gradle](https://gradle.org/gradle-download/)
    * Download the latest version of [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) 
    
    For Mac users: 
       
    * Install Gradle with [Homebrew](http://brew.sh/) (for MacOS)
    ```bash
    $ brew install gradle
    ```
    
    * Install ChromeDriver with Homebrew:
    ```bash
     $ brew install chromedriver
     ```
    
3. Store Sauce Labs credentials:
    * In the terminal, export your Sauce Labs credentials as environmental variables:
    ```bash
    $ export SAUCE_USERNAME=<your Sauce Labs username>
    $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```
    
    * or add two new Environment Variables if you using Windows (restart required).

4. Local ChromeDriver configuration:

   For running tests locally on Windows based environment: 

    * Under 'utils' folder create a new directory, with name that match your ChromeDriver version (e.g. "2.27")
    * Copy ChromeDriver executable to created folder.
    * Update *ChromeDriverVersion* in **test.properties** file:

    ```
    ChromeDriverVersion = 2.27
    ```
    
   On Mac: 
   
   * Feel free to use ChromeDriver installed by Homebrew. 
   However, if you need specific ChromeDriver version, 
   remove Chromedriver installed by Homebrew and follow set-up instruction for Windows. 
   
   ```bash
   $ brew remove chromedriver
   ```

#### Running

```bash
$ gradle clean e2e
``` 

Use --info flag for observing the test output in console:

```bash
$ gradle clean e2e --info
```
    
#### Debug

Use flag --debug-jvm for running gradle task, then attach your IDE to process at address 5005:

```bash
$ gradle clean e2e --debug-jvm 
```

#### Resources

##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Junit Documentation](http://junit.org/javadoc/latest/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

##### [jBehave Documentation](http://jbehave.org/reference/stable/)

##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

*This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub.*
