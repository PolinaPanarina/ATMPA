# Test Automation Framework (TAF) with Java, Selenium, JUnit 5

This is a Test Automation Framework (TAF) developed using Java, Selenium, JUnit 5, RestAssured

## Requirements

- Implement core, business and test levels

Integrate below components into framework (using correct layer):

- Logger
- Reporter
- Test runner
- Configuration
- Utilities 

## Technologies
- Java 17
- JUnit5
- Selenium
- Sel4j, logback
- Allure
- RestAssured
- gradle

## How to Run:
- To build TAF without tests:
  ```./gradlew build --exclude-task test```
- To build TAF with tests:
```./gradlew build```