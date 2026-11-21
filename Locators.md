# Lesson 3 – Locators

## Goal
Learn to find elements on a web page using id, name, CSS selector, and XPath, and know which locator is most stable.

## Practice site
Amazon – https://www.amazon.com

## Steps

### Step 1 – Open Amazon
1. Create a ChromeDriver: `WebDriver driver = new ChromeDriver();`
2. Open Amazon: `driver.get("https://www.amazon.com");`
3. Maximize the window: `driver.manage().window().maximize();`

### Step 2 – Search for a laptop (By id)
1. Create an explicit wait of 10 seconds: `new WebDriverWait(driver, Duration.ofSeconds(10))`
2. Wait for the search box to be visible: `By.id("twotabsearchtextbox")`
3. Type "Laptop": `sendKeys("Laptop")`
4. Press Enter: `sendKeys(Keys.ENTER)`

### Step 3 – Open the Sort by dropdown (By XPath)
1. Find the dropdown: `By.xpath("//span[@class='a-button-text a-declarative']")`
2. Click it: `click()`

### Step 4 – Select a sort option (By CSS selector)
1. Find the option: `By.cssSelector("a[class='a-dropdown-link a-active']")`
2. Click it: `click()`
3. Print a message: `System.out.println("dropdown is clicked");`

### Step 5 – Open the first laptop (By XPath with text)
1. Find the product by part of its title: `By.xpath("//span[contains(text(),'AI Laptop with Office 365')]")`
2. Click it: `click()`

### Step 6 – Close the browser
1. `driver.quit();`

## What I learned
- All four locators used in one script: id for the search box, XPath for the dropdown and the product, CSS selector for the sort option.
- Stability order: id > name > CSS selector > XPath.
- Use relative XPath only — never absolute XPath.
- `By.name()` matches the HTML `name` attribute, NOT the visible text on the page.
- To find an element by its visible text, use XPath `contains(text(),'...')` or `By.partialLinkText("...")`.
- Always use an explicit wait (`WebDriverWait`) before finding an element that may load slowly.
- Use only part of a product title in a locator — full titles change, and characters like `"` break selectors.
- On Amazon, the search button is an `<input>`, not a `<button>` — so `button[type='submit']` does not exist there.

## Notes
- `driver.getTitle()` returns the page title — it does nothing unless you print it.
- After choosing a sort option the results reload, so add a wait before the first-laptop step if it fails.
