# Lesson 6 – Waits

## Goal
Replace sleep calls with explicit waits, and explain implicit versus explicit waits.

## Practice site
Selenium web form – https://www.selenium.dev/selenium/web/web-form.html

## Implicit vs explicit waits
- **Thread.sleep()** — a fixed pause. Always waits the full time, even if the element is ready in 1 second. Slow and unreliable — never use it to wait for elements.
- **Implicit wait** — set once, applies to every `findElement` call. Selenium waits up to 10 seconds for any element to appear. Simple, but the same wait for everything, and it only waits for the element to exist — not to be visible or clickable.
- **Explicit wait** — waits for one specific condition on one element, e.g. wait until the button is clickable. More precise and faster, because it continues as soon as the condition is true.
- **Fluent wait** — like an explicit wait, but you choose how often to check (polling) and which errors to ignore while waiting.

## Steps

### Step 1 – Set up the project
1. Open the `waits` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Open the practice form
1. Create a ChromeDriver: `WebDriver driver = new ChromeDriver();`
2. Maximize the window: `driver.manage().window().maximize();`
3. Open the form: `driver.get("https://www.selenium.dev/selenium/web/web-form.html");`
4. Print the page title: `System.out.println("Title: " + driver.getTitle());`

### Step 3 – Set an implicit wait
1. `driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));`
2. Now every `findElement` waits up to 10 seconds for the element to appear.

### Step 4 – Replace sleep with an explicit wait (text field)
1. Create a wait: `new WebDriverWait(driver, Duration.ofSeconds(10))`
2. Wait for the field to be visible, then type: `wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-text-id")))`
3. No `Thread.sleep()` needed — the wait continues as soon as the field is visible.

### Step 5 – Wait until clickable (Submit button)
1. `wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")))`
2. Click it.

### Step 6 – Wait for the confirmation message
1. `wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")))`
2. Print the text — expected "Received!"
`
### Step 7 – Close the browser
1. `driver.quit();`

## What I learned
- `Thread.sleep()` always waits the full time — slow and unreliable.
- Implicit wait: one setting for the whole driver, waits only for the element to exist.
- Explicit wait: waits for a specific condition on a specific element, continues as soon as it is true.
- Don't mix implicit and explicit waits in real tests — the timeouts add up and make waits unpredictable. Use explicit waits.
