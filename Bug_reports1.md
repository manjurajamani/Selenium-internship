# Lesson 12 – Bug reports

## Goal
Write 3 clear bug reports with steps, expected and actual results, severity and screenshots.

## What is a bug report?
A bug report tells the developer exactly what is wrong, so they can fix it without guessing. A clear bug report has:
- **Title** — one line that says what is wrong.
- **Steps to reproduce** — the exact steps that make the bug happen.
- **Expected result** — what should happen.
- **Actual result** — what really happens.
- **Severity** — how serious the bug is (Critical / High / Medium / Low).
- **Screenshot** — a picture of the bug, so the developer can see it.

**Severity levels:**
- **Critical** — the app crashes or a main feature does not work at all.
- **High** — a main feature works wrongly (wrong result, data loss).
- **Medium** — a feature works, but badly (slow, confusing, security risk).
- **Low** — small problem (wording, layout) that does not block the user.

**Practice site:** SauceDemo – https://www.saucedemo.com/

**Code:** `Bug_reports1.java` (package `poc`) — one test per bug. Each test prints its bug report with the real values and saves a screenshot in the `screenshots` folder.

---

## BUG-01: Username and password fields are not cleared after a failed login

- **Severity:** Medium
- **Environment:** SauceDemo login page, Chrome

**Steps to reproduce:**
1. Open https://www.saucedemo.com/
2. Enter username: `standard_user`
3. Enter password: `wrong_password`
4. Click the **Login** button.

**Expected result:**
The error message is shown and both fields are cleared, so the username and password are not left on the screen.

**Actual result:**
The error message is shown, but the entered username and password stay in their fields.

**Screenshot:** `screenshots/fields-not-cleared.png`

---

## BUG-02: Products page loads very slowly after login (performance_glitch_user)

- **Severity:** Medium
- **Environment:** SauceDemo, Chrome

**Steps to reproduce:**
1. Open https://www.saucedemo.com/
2. Enter username: `performance_glitch_user`
3. Enter password: `secret_sauce`
4. Click the **Login** button.

**Expected result:**
The Products page loads within 3 seconds.

**Actual result:**
The Products page takes about 5760ms to load. The user sees a blank/loading screen for a long time.

**Screenshot:** `screenshots/slow-page-load.png`

---

## BUG-03: Error messages show "Epic sadface:" — confusing wording for users

- **Severity:** Low
- **Environment:** SauceDemo login page, Chrome

**Steps to reproduce:**
1. Open https://www.saucedemo.com/
2. Leave the username field empty.
3. Enter password: `secret_sauce`
4. Click the **Login** button.

**Expected result:**
A clear error message: "Username is required".

**Actual result:**
The error message is "Epic sadface: Username is required". The words "Epic sadface" are confusing and unprofessional for users.

**Screenshot:** `screenshots/error-message-wording.png`

---

## What I learned
- A bug report must let the developer reproduce the bug without asking questions — title, steps, expected result, actual result.
- **Expected vs actual** is the heart of the report: what should happen, and what really happens.
- **Severity** tells the team what to fix first: Critical and High before Medium and Low.
- A **screenshot** shows the bug faster than words — Lesson 9's screenshots on failure do this automatically.
- One bug per report — never mix two bugs in one report.