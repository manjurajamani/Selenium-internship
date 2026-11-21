# Lesson 5 – Assertions

## Goal
Use TestNG assertions to check page titles, texts, and URLs after actions.

## Practice site
Selenium web form – https://www.selenium.dev/selenium/web/web-form.html

## What is POM?
Page Object Model (POM) means one class per page, with that page's locators and actions inside it. Tests call page methods like `login_page.login(user, pass)` instead of finding elements themselves — like a TV remote: you press "login" without knowing the wiring inside. If the UI changes, you update one page class, not every test. Assertions stay in the test, not in the page class.

## Steps

### Step 1 – Set up the project
1. Open the `assertions` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0 and TestNG 7.8.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Set up the test (BeforeMethod)
1. Create a ChromeDriver and maximize the window.
2. Create an explicit wait of 10 seconds.
3. Open the form: `driver.get("https://www.selenium.dev/selenium/web/web-form.html");`
4. This runs before every test, so each test starts fresh.

### Step 3 – Assert the page title
1. Get the title: `driver.getTitle()`
2. Check it: `Assert.assertEquals(driver.getTitle(), "Web form")`
3. If the title is different, the test fails with your message.

### Step 4 – Submit the form, then assert the text and URL
1. Type your name into the text field: `By.id("my-text-id")`
2. Click Submit: `By.xpath("//button[@type='submit']")`
3. Check the confirmation text: `Assert.assertEquals(message.getText(), "Received!")`
4. Check the URL changed: `Assert.assertTrue(driver.getCurrentUrl().contains("submitted-form"))`

### Step 5 – Close the browser (AfterMethod)
1. `driver.quit();`
2. This runs after every test, so the browser always closes.

### Step 6 – Run the tests
1. Right-click the test class in IntelliJ and select Run, or run `mvn test`.
2. Both tests should pass: green tick for the title test and the form test.

## What I learned
- An assertion checks that something is true — if it is not, the test fails.
- `assertEquals(actual, expected)` checks two values are the same (title, text).
- `assertTrue(condition)` checks a condition is true (URL contains "submitted-form").
- `@BeforeMethod` runs before each test; `@AfterMethod` runs after each test.
- In POM, locators and actions live in page classes; assertions live in tests.

