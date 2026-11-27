Lesson 13 – Page Object Model

## Goal
Refactor your tests to the Page Object Model with one class per page.

## What is the Page Object Model (POM)?
POM is a design pattern where **each page of the website gets its own class**. The page class holds:
- the **locators** for that page (id, CSS, XPath), and
- the **actions** for that page (type, click, read text).

The test class only calls the page methods — it never touches locators directly. Think of it like a TV remote: the test presses buttons (methods), and the page class does the work inside.

**Why POM?**
- **Easy to maintain** — if the UI changes (a locator changes), you update it in **one place** (the page class), not in every test.
- **Readable tests** — tests read like steps: `loginpage.Login(...)`, `productpage.gettitletext()`.
- **Reusable** — the same page methods are used by many tests.
- **Assertions stay in the tests**, not in the page classes.

## Classes in this lesson

| Class | What it is |
|---|---|
| `POM_Loginpage` | SauceDemo login page: locators + actions (`enterusername`, `enterpassword`, `clicklogin`, `Login`, `errortext`) |
| `POM_productspage` | SauceDemo products page: locator + action (`gettitletext`) |
| `POM_Logintest` | The tests — 4 login tests using the page classes (valid, wrong password, empty username, locked-out) |

**Practice site:** SauceDemo – https://www.saucedemo.com/
