POC of Java based framework for E2E UI testing of http://demos.telerik.com/kendo-ui/websushi/ website.

This project is meant for educational purposes only. 

##### Dependencies

    selenium = "2.49.0"
    assertj = "3.6.1"
    jbehave = "4.0.5"
    junit = "4.11"
    jehaveWebSelenium = "3.5.5"

##### System requirements

* Java 8
* Gradle
* Chrome browser

##### ChromeDriver configuration

* Under 'utils' folder create a new directory, with name that match your ChromeDriver version (e.g. "2.27")

* Download the latest version of ChromeDriver from:
  https://sites.google.com/a/chromium.org/chromedriver/downloads

* Copy ChromeDriver executable to created folder.

* Update *ChromeDriverVersion* in **test.properties** file:

        ChromeDriverVersion = 2.27

##### Running

$ gradle clean test

or

$ gradle clean test --info

for observing the test output in console.
