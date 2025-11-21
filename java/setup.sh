#!/bin/bash

echo "Setting up Java E2E Automation Framework..."

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven first."
    echo "On macOS: brew install maven"
    exit 1
fi

# Install dependencies
mvn clean install -DskipTests

# Create necessary directories
mkdir -p screenshots reports test-data

echo "Setup complete!"
echo ""
echo "To run tests: mvn test"
echo "To generate Allure report: mvn allure:serve"
