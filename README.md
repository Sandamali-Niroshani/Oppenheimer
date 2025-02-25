# THE OPPENHEIMER PROJECT

## Introduction
This project is able to hand API requeste, database query and UI activities of the application.
This project is built using Java, Selenium WebDriver, Rest Assured, SQL query and TestNG.
It is designed for automating Oppenheimer web applications and follows best practices such as 
Page Object Model (POM) 
TestNG annotations and
OOP concepts.

## Features
- Selenium WebDriver for web automation
- Rest Assured for API automation
- SQL query for database query
- TestNG for test execution and Assertions
- Page Object Model (POM) and OOP concepts for maintainability
- Maven for dependency management
- Use TestNG annotations for setup and teardown(BaseTest.java)
- Capture screenshots as needed and stored in `screenshots/` folder
- Handle testdata using json file
- Maintain config file, manage reusable method separately(pageBase.java)

## Technologies Used
- Java 11
- Selenium
- Rest Assured
- TestNG
- SQL
- Maven

## Prerequisites
Ensure you have the following installed:
- Java JDK (Using java 11 for this project)
- Maven
- IDE (IntelliJ IDEA)
- Google Chrome
- ChromeDriver (compatible with your Chrome version) stored in `drivers/` folder
- Make sure application and database is up.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Sandamali-Niroshani/Oppenheimer.git
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```
## Running Tests
### Using IDE:
1. Run using TestNG.xml file
    - Right-click on `TestNG.xml` file and select **Run 'TestNG.xml'**
2. Execute individual testcases 
    - Right-click on testcase and select Run
3. Execute through cmd
    - Open a terminal
    - Run the command: mvn test
    - Surefire report will be available at: target/surefire-reports/index.html
    - Open the report in a browser to view test results(This is not a detailed report to get better idea about scripts)

## Project Structure
```

Oppenheimer/
│-- src/
│   ├── main/
│   │   ├── java/
│   ├── test/
│   │   ├── java/
│   │   │   ├── api/                 # API automation
│   │   │   │     ├── function/      # API functions
│   │   │   │     ├── queryHelper/   # SQL query
│   │   │   ├── payload/             # Payloads for API request
│   │   │   ├── web/                 # Web automation
│   │   │   │     ├── pages/         # Page Object Model (POM) classes and BasePage class
│   │   │   ├── resources/           
│   │   │         ├── testdata/      # Test data in json file format
│   │   │         ├── download/      # Downloaded files
│   │   │         ├── files/         # Files to upload
│   │   │   ├── testScripts/         # TestNG test scripts and BaseTest class for setup and teardown         
│   │   │   ├── utility/             # ReadConfig class to read configuration file and TestDataReader class to read test data                                  
│-- configuration/                # Configuration files (config.properties) 
│-- drivers/                      # ChromeDriver
│-- screenshots/                  # Screenshots captured during test execution
|-- pom.xml                       # Maven dependencies
│-- TestNG.xml                    # TestNG suite configuration
│-- README.md
```

## Explanation of the project
### Project Structure
Created api and web two folders to separate API and Web automation functions.

- **src/main/test/java/**: Contains main codebase.
- **api/**: Contains API automation functions.
- **api/function/**: Contains API functions to perform API operations.
- **api/queryHelper/**: Contains SQL query functions to perform database operations.
- **payload/**: Contains payloads for API requests.
- **web/**: Contains Web automation functions.
- **web/pages/**: Contains Page Object Model (POM) classes for each page of the application. 
- **web/PageBase.java**: Contains common methods for all pages.
- **resources/**: Contains test data in json file format.
- **resources/download/**: Contains downloaded files.
- **resources/files/**: Contains files to upload.
- **testScripts/**: Contains TestNG test scripts.
- **testScripts/BaseTest.java**: Contains setup and teardown methods.
- **utility/**: Contains ReadConfig class to read configuration file and TestDataReader class to read test data.
- **utility/ReadConfig.java**: Contains ReadConfig class to read configuration file.
- **utility/JsonUtils.java**: Contains JsonUtils class to read json file.
- **utility/DynamicData.java**: Contains DynamicData class to generate dynamic data.
- **utility/DBUtility.java**: Contains DBUtility class to perform database operations.
- **utility/CSVReader.java**: Contains CSVReader class to read CSV file.
- **utility/Constants.java**: Contains Constants class to store constants.
- **configuration/**: Contains configuration files (config.properties). 
- **drivers/**: Contains ChromeDriver.
- **screenshots/**: Contains screenshots captured during test execution.
- **pom.xml**: Maven dependencies.
- **TestNG.xml**: To execute testcases using TestNG suite configuration.
- **README.md**: Project documentation.


### Test Case

**Design of testcase**

- Testcase extends BaseTest class.
- The test case has been drafted according to the User Story.
- For each User Story, a separate automation script has been created.
- Natid, voucher name generated dynamically.
- To create heroes using csv file, upload multiple heroes using csv file. CSV file data
can reused as everytime created heroes deleted.

- **UserStory1.java**
    - Test methods are designed to test the following scenarios.
        - createHeroSuccess(): Test method to create a hero and verify the hero is created successfully.
        - createHeroFailure(): Test method to create a hero with invalid data and verify the hero is not created
        - getHeroById(): Test method to get a hero by id and verify the hero details


- **UserStory2.java**
     - Test methods are designed to test the following scenarios.
         - createMultipleHeroesViaCSV(): Test method to create multiple heroes using CSV file and verify the heroes are created successfully.
         - createMultipleHeroesWithErrorCSV(): Test method to create multiple heroes using CSV file with invalid data and verify the heroes are not created.


- **UserStory3.java**
    - Test methods are designed to test the following scenarios.
         - generateTaxRelifFileAndVerify(): Test method to generate tax relief file and verify the file is generated successfully.


- **UserStory4.java**
     - Test methods are designed to test the following scenarios.
         - createWorkingHeroWithVoucher(): Test method to create a working hero with voucher and verify the hero is created successfully.
         - createWorkingHeroWithInvalidVoucher(): Test method to create a working hero with invalid voucher and verify the hero is not created.


- **UserStory5.java**
    - Test methods are designed to test the following scenarios.
         - createHeroSuccess(): Test method to create a hero and verify the hero is created successfully.
         - workingClassHeroOwesMoney(): Test method to retrieve a working class hero owes and verify the response.
         - workingClassHeroOwesMoneyWithInvalidVoucher(): Test method to verify working class hero owe accept only numeric value.


- **UserStory6.java**
     - Test methods are designed to test the following scenarios.
         - verifyVouchersByPersonAndType(): Test method to retrieve vouchers by person and type.
         - Verify the response format.




