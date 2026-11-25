# Lesson 9 – Screenshots on failure

## Goal
Save a screenshot automatically whenever a test fails.

## Practice site
SauceDemo login – https://www.saucedemo.com/
- Valid user: `standard_user` / `secret_sauce`

## Files
- `ScreenshotOn_Failure.java` — the test class (package `poc`).
- `pom.xml` — Selenium Java 4.25.0 and TestNG 7.8.0.

## How it works
- **TakesScreenshot** — cast the driver to `TakesScreenshot` and call `getScreenshotAs(OutputType.FILE)`. That gives you a picture of the browser at that moment.
- **ITestResult** — TestNG passes the result of each test into `@AfterMethod`. `result.getStatus() == ITestResult.FAILURE` tells you the test failed, and `result.getName()` gives you the test method name.
- **@AfterMethod** — runs after every test, pass or fail. That is where the screenshot check lives: if the test failed, take the screenshot; then close the browser.

## Steps

### Step 1 – Set up the project
1. Open the `screenshots-on-failure` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0 and TestNG 7.8.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Open the browser in setup()
1. Declare the driver and the wait at class level: `WebDriver driver;` and `WebDriverWait wait;`
2. In `@BeforeMethod setup()`, assign them — no type in front: `driver = new ChromeDriver();`
3. Create the wait for 2 seconds: `wait = new WebDriverWait(driver, Duration.ofSeconds(2));`
4. Maximise the window and open the login page: `driver.get("https://www.saucedemo.com/");`

### Step 3 – Write a passing test (valid login)
1. `testValidLogin` logs in with `standard_user` / `secret_sauce` using the `login()` helper.
2. Wait up to 2 seconds until the title is visible: `wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")))`
3. Assert the title is "Products".
4. This test passes, so no screenshot is saved.

### Step 4 – Write a failing test on purpose (wrong password)
1. `testInvalidLogin` logs in with `standard_user` / `wrong_password`.
2. Wait up to 2 seconds until the error is visible: `wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")))`
3. Assert the error contains "Wrong message" — this is wrong on purpose, so the test fails and a screenshot is saved.

### Step 5 – Log in with the login() helper
1. `login()` takes a username and password.
2. It types the username into `By.id("user-name")`, the password into `By.id("password")`, and clicks `By.id("login-button")`.
3. Both tests use this helper, so the login steps are written only once.

### Step 6 – Take a screenshot in @AfterMethod when the test fails
1. Give `@AfterMethod` the test result: `public void tearDown(ITestResult result)`
2. Check the status: `if (result.getStatus() == ITestResult.FAILURE)`
3. If it failed, call `takeScreenshot(result.getName())` — the file is named after the test.
4. Always close the browser: `driver.quit();`

### Step 7 – Save the screenshot to a file
1. Cast the driver: `((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)`
2. Build the path: `Path.of("screenshots", testName + ".png")`
3. Create the folder if it does not exist: `Files.createDirectories(destination.getParent());`
4. Copy the file there: `Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);`
5. Print the saved path so you can find it: `System.out.println("Screenshot saved: " + destination.toAbsolutePath());`

### Step 8 – Run the tests and see the screenshot
1. Right-click `ScreenshotOn_Failure` in IntelliJ and select Run, or run `mvn test`.
2. `testValidLogin` passes (no screenshot).
3. `testInvalidLogin` fails and a screenshot is saved as `screenshots/testInvalidLogin.png`.
4. Where to see it: in IntelliJ, open the `screenshots` folder in the Project panel; or in Windows File Explorer, go to `D:\automation\Selenium-internship\screenshots\`.

## What I learned
- A screenshot shows exactly what the page looked like when the test failed — much easier than guessing from an error message.
- `TakesScreenshot` + `getScreenshotAs(OutputType.FILE)` takes the picture.
- `ITestResult` in `@AfterMethod` tells you if the test passed or failed.
- The screenshot runs automatically for every failing test — no extra code in the tests themselves.
- Name the file after the test, so you know which failure each picture belongs to.
- `wait.until(ExpectedConditions.visibilityOfElementLocated(...))` waits up to 2 seconds for the element — and continues as soon as it appears, so it is faster than sleep.
- `Duration.ofSeconds(2)` counts seconds, not milliseconds — 2 seconds, not 2000.
- Declare the driver at class level and assign it in `setup()` — never write `WebDriver driver = new ChromeDriver();` inside a method, or the class-level driver stays null.