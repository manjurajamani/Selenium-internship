**Test Pyramid**

<img width="664" height="496" alt="image" src="https://github.com/user-attachments/assets/c6cff6d2-286b-4fe1-be92-efc631fc2df2" />

**1. Levels of Testing Pyramid**

**1. Unit Testing – Bottom Layer**
- Tests individual methods, functions, or components.
- Usually written and executed by developers.
- Fast execution.
- Easy to identify defects.
- Examples: JUnit, TestNG, Mockito.
  
**2. Integration Testing – Middle Layer**

- Tests communication between different modules or components.
- Verifies whether integrated components work together correctly.
- Execution is slower than unit testing.
- Examples: REST Assured, API integration tests, Spring integration tests.
  
**3. UI / End-to-End Testing – Top Layer**
- Tests the complete application from the user's perspective.
- Verifies end-to-end business workflows.
- Slower execution and higher maintenance.
- Examples: Selenium, Playwright, Cypress.
  
**2. Characteristics of Each Testing Level**
  
**Unit Testing**
- Focuses on testing individual methods, functions, or components.
- Usually performed by developers.
- Executes quickly.
- Helps identify defects at an early stage.
- Requires minimal setup.
- Examples: JUnit, TestNG, Mockito.
  
**Integration Testing**
- Focuses on testing communication between different modules.
- Verifies whether multiple components work together correctly.
- Identifies issues in data flow and API communication.
- Requires more setup than unit testing.
- Examples: REST Assured, Spring Test.
 
**UI / End-to-End Testing**
- Focuses on testing complete application workflows.
- Simulates real user interactions.
- Verifies the application from start to finish.
- Takes more time to execute.
- Requires more maintenance.
- Examples: Selenium, Playwright, Cypress.
  
**3. Real-World Example: Banking Application**
  
Consider a banking application with a money transfer feature.

- Unit Testing: Verify whether the transfer amount calculation is correct.
- Integration Testing: Verify whether the banking API correctly communicates with the account and transaction services.
- UI Testing: Log in, select a beneficiary, enter the amount, transfer money, and verify the confirmation message.
  
**4. Why Is It Called a Pyramid?**

The pyramid represents the recommended distribution of automated tests:
- Bottom: Many unit tests because they are fast and inexpensive.
- Middle: A moderate number of integration tests.
- Top: Fewer UI tests because they are slower and more expensive to maintain.
  
**Key Takeaway:** Build a strong foundation with unit tests, add integration tests to verify component communication, and use UI automation for important end-to-end business scenarios.
  
