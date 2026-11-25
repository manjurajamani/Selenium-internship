# Lesson 11 – Git for test code

## Goal
Keep the test project in Git with a clear folder structure, README and .gitignore.

## Why Git for test code?
- **History** — every change is saved with a message, so you can see what changed and go back if something breaks.
- **Backup** — the code lives on GitHub, not only on one laptop.
- **Sharing** — anyone can open the repo and see the tests and lesson notes.
- **Clean repo** — only code and notes go in; build files, reports and screenshots stay out (that is the `.gitignore`).

## Repo structure

Selenium-internship/
├── .gitignore
├── README.md (main README — what the repo is, how to run)
├── src/ (the Java test code, package poc)
├── screenshots/ (screenshots saved by failing tests)
├── TestingBasics.md (Lesson 1)
├── TestPyramid.md (Lesson 1)
├── When Automation Testing PaysOff.md (Lesson 1)
├── Setup_Steps.md (Lesson 2)
├── Locators.md (Lesson 3)
├── BasicActions.md (Lesson 4)
├── Assertions.md (Lesson 5)
├── Testcases.md (Lesson 7)
├── Alerts_frames_windows.md (Lesson 8)
├── ScreenshotOn_Failure.md (Lesson 9)
├── Test_Framework.md (Lesson 10)
└── git-for-test-code/ (Lesson 11 — this folder)


## The .gitignore file
The repo already has a `.gitignore` (the default one — it ignores `target/`, `.idea/` files and Eclipse files). For test code, two more lines are needed — add them at the end:

TestNG reports — regenerated on every test run
test-output/

Screenshots taken by failing tests — pictures, not code
screenshots/


The `.gitignore` tells Git which files to ignore. Build output, IDE settings, reports and screenshots are regenerated on every run, so they do not belong in the repo.

## Steps (done in IntelliJ IDEA)

### Step 1 – Edit the .gitignore
1. Open the project in IntelliJ IDEA.
2. Open the `.gitignore` file (in the project root, next to `pom.xml`).
3. Scroll to the bottom and add the two lines:
   test-output/
   screenshots/
4. 4. Save with **Ctrl+S**.

### Step 2 – Add the main README
1. Right-click the project folder (Project panel) → **New** → **File**.
2. Type the file name: `README.md`
3. Paste the main README (what the repo is, the lesson list, how to run).
4. Save with **Ctrl+S**.

### Step 3 – Create the Lesson 11 folder
1. Right-click the project folder → **New** → **Directory**.
2. Type the name: `git-for-test-code`
3. Right-click the new `git-for-test-code` folder → **New** → **File**.
4. Type the file name: `README.md`
5. Paste this README.
6. Save with **Ctrl+S**.

### Step 4 – Commit and push
1. Click **Git** in the top menu → **Commit...**
2. Tick the files (`.gitignore`, `README.md`, `git-for-test-code/README.md`).
3. Write the message: `Add Lesson 11: Git for test code with .gitignore and README`
4. Click **Commit**.
5. Click **Git** in the top menu → **Push...** → **Push**.

### The Git cycle, in one line
`git status` → `git add .` → `git commit -m "message"` → `git push` — and `git pull` to get the latest.

- `git status` shows which files are new or changed.
- `git add .` stages all changed files — "these are the files I want to save".
- `git commit -m "message"` saves a snapshot. The message should say what changed.
- `git push` sends the commits to GitHub.
- `git pull` brings the newest code from GitHub to your laptop — run it before you start work.

## What I learned
- Git saves the history of the code — every commit is a snapshot with a message.
- A clear structure (code in `src/`, one note per lesson, each lesson in its own folder where needed) makes the repo easy to read.
- `.gitignore` keeps build files, reports and screenshots out of the repo — only code and notes go in.
- `git status` shows what changed before you save anything.
- A good commit message says what changed, in one short line.
- `git push` sends the work to GitHub; `git pull` brings the latest back.