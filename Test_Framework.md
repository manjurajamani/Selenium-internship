# Lesson 10 – Test framework

## Goal
Organise tests with TestNG: setup and teardown in a base class, groups, and a simple report.

## Practice site
SauceDemo login – https://www.saucedemo.com/
- Valid user: `standard_user` / `secret_sauce`
- Locked user: `locked_out_user` / `secret_sauce`

## Files
- `Test_Framework_BaseTest.java` — setup, teardown and screenshot on failure (shared by all test classes).
- `Test_Framework_LoginTest.java` — the tests only, grouped into `smoke` and `regression`.
- `testng.xml` — the suite file: which groups run and which classes run.
- `pom.xml` — Selenium Java 4.25.0 and TestNG 7.8.0.

## What is a test framework?
A test framework is the structure around your tests — not the tests themselves. It answers three questions:
- **Where does setup live?** In a base class (`Test_Framework_BaseTest`), so every test class shares it.
- **Which tests run when?** In groups (`smoke`, `regression`), chosen in `testng.xml`.
- **How do I see the results?** In a report — TestNG makes one automatically (`test-output/index.html`).

## Steps

### Step 1 – Set up the project
1. Open the `test-framework` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0 and TestNG 7.8.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Create the base class (Test_Framework_BaseTest)
1. Declare the driver and the wait at class level: `WebDriver driver;` and `WebDriverWait wait;`
2. `@BeforeMethod setup()` runs before every test: opens Chrome, maximises the window, creates the wait for 10 seconds (`wait = new WebDriverWait(driver, Duration.ofSeconds(10));`), and opens the login page.
3. `@AfterMethod tearDown()` runs after every test: if the test failed, saves a screenshot named after the test, then closes the browser.
4. `takeScreenshot()` takes the picture with `TakesScreenshot` and saves it in the `screenshots` folder.

### Step 3 – Create the test class (Test_Framework_LoginTest)
1. `Test_Framework_LoginTest extends Test_Framework_BaseTest` — it gets setup, teardown, screenshots, the driver and the wait for free. No own `setup()` needed.
2. Each test has a group: `smoke` for the must-pass check, `regression` for the full checks.
3. `testvalidlogin` is in both groups — it is a smoke test AND a regression test.
4. Each test uses the wait from the base class: `wait.until(ExpectedConditions.visibilityOfElementLocated(...))` — the wait is created only once, in `setup()`, never again inside a test.
5. No assert is used — each check is a simple if/else that prints PASS or FAIL, and throws `new AssertionError(...)` on failure so TestNG still marks the test as failed.

### Step 4 – The four tests
1. `testvalidlogin` (smoke + regression) — logs in with `standard_user` / `secret_sauce`, waits for the "Products" title (`//span[@class='title']` — the title is a `<span>`, not an `<input>`), checks it says "Products".
2. `testInvalidPassword` (regression) — logs in with a wrong password, checks the error contains "Epic sadface: Username and password do not match any user in this service".
3. `test_emptyusername` (regression) — logs in with no username, checks the error contains "Username is required".
4. `testLockedUser` (regression) — logs in with `locked_out_user`, checks the error contains "locked out".

### Step 5 – Log in with the login() helper
1. `login()` takes a username and password.
2. It types the username into `By.id("user-name")`, the password into `By.id("password")`, and clicks `By.id("login-button")`.
3. All four tests use this helper, so the login steps are written only once.

### Step 6 – Create the suite file (testng.xml)
1. `testng.xml` sits in the project root (next to `pom.xml`).
2. The `<groups>` part says which groups to run — here, both `smoke` and `regression`.
3. The `<classes>` part says which test classes to run — here, `poc.Test_Framework_LoginTest`.
4. To run ONLY the smoke tests, delete the `<include name="regression"/>` line.

### Step 7 – Run the tests
1. Right-click `testng.xml` in IntelliJ and select Run, or run `mvn test`.
2. All 4 tests should pass.
3. To run only the smoke group, remove the regression line from `testng.xml` and run again — only `testvalidlogin` runs.

### Step 8 – See the report and the screenshots
1. After the run, TestNG creates a `test-output` folder in your project.
2. Open `test-output/index.html` in a browser — it shows how many tests passed, failed and were skipped, with the time each took.
3. If a test fails, its screenshot is saved in the `screenshots` folder, named after the test — in IntelliJ, open the `screenshots` folder in the Project panel; or in Windows File Explorer, go to `D:\automation\Selenium-internship\screenshots\`.

## What I learned
- A framework is structure: base class + groups + suite file + report.
- The base class holds setup, teardown and screenshots — test classes stay short.
- Groups let you run a quick smoke check or a full regression check from the same tests.
- `testng.xml` decides what runs — change the groups there, not in the code.
- TestNG makes a simple HTML report automatically — no extra code needed.
- The wait is created ONCE in `setup()` (`Duration.ofSeconds(10)`) and used with `wait.until(...)` in the tests — never create it again inside a test.
- `Duration.ofSeconds(10)` counts seconds, not milliseconds — 10 seconds, not 2000.
- Without assert, use if/else with `throw new AssertionError(...)` — otherwise TestNG shows a failing test as passed.
- The "Products" title is a `<span>`, not an `<input>` — always check the real tag name first.
- Declare the driver at class level and assign it in `setup()` — never write `WebDriver driver = new ChromeDriver();` inside a method, or the class-level driver stays null.