# Java E2E Automation Framework

Complete automation framework using Selenium WebDriver, TestNG, Maven, and Page Object Model.

## Features
- ✅ Page Object Model (POM) architecture
- ✅ E2E test automation (Search → Add to Cart → Screenshot → Excel)
- ✅ Screenshot capture functionality
- ✅ Excel data export with Apache POI
- ✅ TestNG & Allure reporting
- ✅ Maven build management

## Setup

1. **Prerequisites:**
   - Java 11 or higher
   - Maven 3.6+

2. **Install dependencies:**
```bash
mvn clean install
```

3. **Install Allure (for reporting):**
```bash
# macOS
brew install allure

# Or download from: https://docs.qameta.io/allure/
```

## Running Tests

### Maven Commands
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=E2EAutomationTest

# Run specific test method
mvn test -Dtest=E2EAutomationTest#testSearchAndAddToCart
```

### Generate Reports
```bash
# Generate Allure report
mvn allure:serve

# Or generate static report
mvn allure:report
```

## Project Structure
```
java/
├── src/
│   ├── main/java/
│   │   ├── pages/      # Page Object classes
│   │   └── utils/      # Utility classes
│   └── test/java/
│       └── tests/      # Test cases
├── screenshots/        # Test screenshots
├── reports/           # Test reports
├── test-data/         # Excel files
├── pom.xml           # Maven configuration
└── testng.xml        # TestNG suite
```

## Test Results
- **Screenshots**: Saved in `screenshots/` folder
- **Excel Data**: Saved in `test-data/` folder
- **Reports**: TestNG and Allure reports in `target/` and `reports/` folders

## Key Test Cases
1. **testSearchAndAddToCart**: Complete E2E workflow
2. **testMultipleItemsSearch**: Batch search testing
