# Lesson 8 – Alerts, frames and windows

## Goal
Handle JavaScript alerts, iframes and new tabs on a practice site.

## Practice site
The Internet (Herokuapp) – https://the-internet.herokuapp.com/
- Alerts: /javascript_alerts
- iFrame: /iframe
- Windows: /windows

## What are they?
- **JavaScript alert** — a browser popup made by JavaScript. Selenium cannot find it with `findElement` — use `driver.switchTo().alert()`. Then `accept()` clicks OK, `dismiss()` clicks Cancel, `getText()` reads the message, and `sendKeys()` types into a prompt alert.
- **iFrame** — an HTML page inside a page. Selenium can only see the main page — use `driver.switchTo().frame()` to go inside, and `driver.switchTo().defaultContent()` to come back out.
- **New window/tab** — every tab has a handle (an ID). `getWindowHandle()` gives the current tab, `getWindowHandles()` gives all tabs. Use `driver.switchTo().window(handle)` to move between them, and `driver.close()` to close the current tab (not `quit()`, which closes everything).

## Steps

### Step 1 – Set up the project
1. Open the `alerts-frames-windows` folder in IntelliJ IDEA.
2. Make sure `pom.xml` has Selenium Java 4.25.0.
3. Run `mvn clean install` (or let IntelliJ import the Maven project) to download the dependencies.

### Step 2 – Simple alert (JS Alert, click OK)
1. Open the JavaScript alerts page.
2. Click the JS Alert button: `By.xpath("//button[@onclick='jsAlert()']")`
3. Switch to the alert: `Alert alert = driver.switchTo().alert();`
4. Print the alert text: `alert.getText()` — expected "I am a JS Alert".
5. Click OK: `alert.accept();`
6. Read the result text: `driver.findElement(By.id("result")).getText()` — expected "You successfully clicked an alert".

### Step 3 – Confirm alert (JS Confirm, click Cancel)
1. Click the JS Confirm button: `By.xpath("//button[@onclick='jsConfirm()']")`
2. Switch to the alert and print its text — expected "I am a JS Confirm".
3. Click Cancel: `alert1.dismiss();`
4. Read the result text — expected "You clicked: Cancel".

### Step 4 – Prompt alert (JS Prompt, type text)
1. Click the JS Prompt button: `By.xpath("//button[@onclick='jsPrompt()']")`
2. Switch to the alert.
3. Type your name: `alert2.sendKeys("Manju");`
4. Print the alert text — expected "I am a JS prompt".
5. Click OK: `alert2.accept();`
6. Read the result text — expected "You entered: Manju".

### Step 5 – iFrame (type inside the frame)
1. Open the iframe page.
2. Switch into the frame by id: `driver.switchTo().frame("mce_0_ifr");`
3. Find the editor: `driver.findElement(By.id("tinymce"))`
4. Clear it and type: `element.sendKeys("Hello World");`
5. Print the edited content: `element.getText()`
6. Switch back to the main page: `driver.switchTo().defaultContent();`
7. Print the page heading: `driver.findElement(By.tagName("h3")).getText()` — expected "An iFrame containing the TinyMCE WYSIWYG Editor".

### Step 6 – New window (switch tabs)
1. Open the windows page.
2. Save the current tab: `String mainWindow = driver.getWindowHandle();`
3. Click the link that opens a new tab: `By.xpath("//a[@target='_blank']")`
4. Print the text of the example div: `driver.findElement(By.xpath("//div[@class='example']")).getText()`
5. Get all tab handles: `Set<String> allWindows = driver.getWindowHandles();`
6. Loop through the handles and switch to the one that is not the main tab.
7. Print the new tab title: `driver.getTitle()` — expected "New Window".
8. Print the new tab heading: `driver.findElement(By.tagName("h3")).getText()` — expected "New Window".
9. Close the new tab: `driver.close();`
10. Switch back to the main tab: `driver.switchTo().window(mainWindow);`
11. Print the main tab title: `driver.getTitle()` — expected "The Internet".

### Step 7 – Close the browser
1. `driver.quit();`

## What I learned
- Alerts are not web elements — switch to them with `driver.switchTo().alert()`.
- `accept()` = OK, `dismiss()` = Cancel, `sendKeys()` types into a prompt.
- Selenium sees only the main page — switch into a frame with `switchTo().frame()` and back with `switchTo().defaultContent()`.
- Each tab has a handle — save the main tab first, then switch by handle.
- `driver.close()` closes one tab; `driver.quit()` closes everything.
- `Thread.sleep()` pauses the code for a fixed time — an explicit wait is better, because it continues as soon as the element is ready.
