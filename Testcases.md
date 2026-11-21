# Lesson 7 – Write test cases

## Goal
Write 15 manual test cases for a login page (valid, invalid, empty, locked), then automate 5 of them.

## Practice site
SauceDemo login – https://www.saucedemo.com/
- Valid user: `standard_user` / `secret_sauce`
- Locked user: `locked_out_user` / `secret_sauce`
- SauceDemo was chosen because it has a real locked-out user, so the locked test case can be checked for real.

## Test credentials

| Username | Password | Purpose |
|---|---|---|
| standard_user | secret_sauce | Successful login |
| locked_out_user | secret_sauce | Locked user validation |

## 1. Login Module

| Test Case ID | Test Scenario | Test Steps | Expected Result |
|---|---|---|---|
| TC_LOGIN_01 | Login with valid credentials | Enter `standard_user` and `secret_sauce`. Click Login. | User should successfully navigate to the Products page. |
| TC_LOGIN_02 | Login with slow user | Enter `performance_glitch_user` and `secret_sauce`. Click Login. | User should successfully navigate to the Products page (page loads slower). |
| TC_LOGIN_03 | Login with valid username and wrong password | Enter `standard_user` and `wrong_password`. Click Login. | Error message "Epic sadface: Username and password do not match any user in this service" should be displayed. |
| TC_LOGIN_04 | Login with wrong username and valid password | Enter `wrong_user` and `secret_sauce`. Click Login. | Error message "Epic sadface: Username and password do not match any user in this service" should be displayed. |
| TC_LOGIN_05 | Login with wrong username and wrong password | Enter `wrong_user` and `wrong_password`. Click Login. | Error message "Epic sadface: Username and password do not match any user in this service" should be displayed. |
| TC_LOGIN_06 | Login with username in uppercase | Enter `STANDARD_USER` and `secret_sauce`. Click Login. | Error message should be displayed (username is case-sensitive). |
| TC_LOGIN_07 | Login with password in wrong case | Enter `standard_user` and `Secret_Sauce`. Click Login. | Error message should be displayed (password is case-sensitive). |
| TC_LOGIN_08 | Login with special characters in username | Enter `test@123` and `secret_sauce`. Click Login. | Error message should be displayed. |
| TC_LOGIN_09 | Login with empty username | Leave username blank. Enter `secret_sauce`. Click Login. | Error message "Epic sadface: Username is required" should be displayed. |
| TC_LOGIN_10 | Login with empty password | Enter `standard_user`. Leave password blank. Click Login. | Error message "Epic sadface: Password is required" should be displayed. |
| TC_LOGIN_11 | Login with both fields empty | Leave both username and password blank. Click Login. | Error message "Epic sadface: Username is required" should be displayed. |
| TC_LOGIN_12 | Login with locked-out user | Enter `locked_out_user` and `secret_sauce`. Click Login. | Error message "Epic sadface: Sorry, this user has been locked out" should be displayed. |
| TC_LOGIN_13 | Login with spaces around username | Enter ` standard_user ` (with spaces) and `secret_sauce`. Click Login. | Error message should be displayed (spaces are not trimmed). |
| TC_LOGIN_14 | Login with very long username | Enter a 100-character username and `secret_sauce`. Click Login. | Error message should be displayed and the page should not crash. |
| TC_LOGIN_15 | Login with SQL injection attempt | Enter `' OR '1'='1` and `secret_sauce`. Click Login. | Error message should be displayed and user should not be logged in. |

## 5 automated test cases
Automated in `LoginTest.java` (TestNG, with explicit waits):
- TC_LOGIN_01 – Valid login (checks the Products page title)
- TC_LOGIN_03 – Invalid password (checks the error message)
- TC_LOGIN_09 – Empty username (checks "Username is required")
- TC_LOGIN_10 – Empty password (checks "Password is required")
- TC_LOGIN_12 – Locked user (checks the locked-out error)

## Steps

### Step 1 – Set up the project
1. Open the `test-cases` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0 and TestNG 7.8.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Write the 15 manual test cases
1. Cover all four groups: valid, invalid, empty, locked.
2. For each case, write the test data and the exact expected result.
3. Use the table above.

### Step 3 – Automate 5 of them
1. One `login()` helper method types the username and password and clicks Login.
2. One `getErrorText()` helper reads the error message with an explicit wait.
3. Each test calls `login()` with different data and asserts the result.
4. `@BeforeMethod` opens the login page fresh for every test; `@AfterMethod` closes the browser.

### Step 4 – Run the tests
1. Right-click the test class in IntelliJ and select Run, or run `mvn test`.
2. All 5 tests should pass.

## What I learned
- A good test case has test data and one clear expected result.
- Login tests need four groups: valid, invalid, empty, and locked.
- Error messages are part of the test — assert the exact message, not just "an error appears".
- Automating 5 cases from the same 15 is easy when the steps are the same and only the data changes — one helper method, different inputs.
