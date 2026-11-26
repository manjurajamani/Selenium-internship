# Selenium Internship

My Selenium Automation Testing internship tasks — one note per lesson, with the Java test code in the `src` folder.

## Tools
- Java 17
- Selenium WebDriver 4.25.0
- TestNG 7.8.0
- Maven
- IntelliJ IDEA

## Repo structure
| File / folder | What it is |
|---|---|
| `src/` | The Java test code (package `poc`) |
| `screenshots/` | Screenshots saved by failing tests |
| `.gitignore` | Tells Git to ignore build files, reports and screenshots |
| `TestingBasics.md` | Lesson 1 – Testing basics: manual vs automated testing |
| `TestPyramid.md` | Lesson 1 – The test pyramid |
| `When Automation Testing PaysOff.md` | Lesson 1 – When automation pays off |
| `Setup_Steps.md` | Lesson 2 – Set up Selenium with Java and Maven |
| `Locators.md` | Lesson 3 – Locators: id, name, CSS selectors, XPath |
| `BasicActions.md` | Lesson 4 – Typing, clicking, dropdowns, checkboxes |
| `Assertions.md` | Lesson 5 – TestNG assertions for titles, texts and URLs |
| `Testcases.md` | Lesson 7 – 15 manual login cases, 5 automated |
| `Alerts_frames_windows.md` | Lesson 8 – JavaScript alerts, iframes and new tabs |
| `ScreenshotOn_Failure.md` | Lesson 9 – Save a screenshot automatically when a test fails |
| `Test_Framework.md` | Lesson 10 – BaseTest, smoke/regression groups, testng.xml, report |
| `git-for-test-code/` | Lesson 11 – Git for test code (folder with its own README) |

## How to run
1. Open the project in IntelliJ IDEA.@
2. Right-click `pom.xml` → Maven → Reload Project (first time only).
3. Right-click a test class (or `testng.xml`) and select Run.
4. TestNG report: open `test-output/index.html` in a browser.
5. If a test fails, its screenshot is saved in the `screenshots` folder.

## Practice sites
- SauceDemo login – https://www.saucedemo.com/
- The Internet – https://the-internet.herokuapp.com/
- Selenium web form – https://www.selenium.dev/selenium/web/web-form.html