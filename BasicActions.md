# Lesson 4 – Basic actions

## Goal
Automate typing, clicking, selecting from a dropdown, and checking a checkbox on a practice form.

## Practice site
Selenium web form – https://www.selenium.dev/selenium/web/web-form.html

## Steps

### Step 1 – Open the practice form
1. Create a ChromeDriver: `WebDriver driver = new ChromeDriver();`
2. Open the form: `driver.get("https://www.selenium.dev/selenium/web/web-form.html");`
3. Print the page title: `System.out.println("Title" + driver.getTitle());`
4. Maximize the window: `driver.manage().window().maximize();`

### Step 2 – Type your name (sendKeys)
1. Find the text field: `By.id("my-text-id")`
2. Click it, then type: `sendKeys("Manju Rajamani")`

### Step 3 – Select from the dropdown (By XPath)
1. Find the dropdown: `By.xpath("//select[@class='form-select']")`
2. Click it to open the options.
3. Find the option: `By.xpath("//option[@value='2']")`
4. Click it to select "Two".

### Step 4 – Check the checkbox (isSelected + click)
1. Find the checkbox: `By.id("my-check-2")`
2. Check if it is already selected: `isSelected()`
3. Click it only if it is not selected.

### Step 5 – Close the browser
1. `driver.quit();`

## What I learned
- `sendKeys()` types text; `click()` clicks buttons, options, and checkboxes.
- A dropdown is a `<select>` element — its options are `<option>` elements you can click directly, or use the `Select` class (`selectByVisibleText()`).
- Always check `isSelected()` before clicking a checkbox, so you don't uncheck it by mistake.
- An XPath must have matching quotes — a missing `'` makes the whole selector invalid (`InvalidSelectorException`).
- I also practised the same actions on the Gmail sign-in page: typing the email id and clicking Next.

## Notes
- `Thread.sleep(2000)` pauses the code for 2 seconds; an explicit wait (`wait.until(...)`) is better because it continues as soon as the element is ready.

