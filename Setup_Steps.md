**Set Up Selenium WebDriver with Java and Maven**

**Step 1: Create a Maven Project**

- Open IntelliJ IDEA.
- Select New Project.
- Choose Maven.
- Select Java JDK 17.
- Enter the project name.
- Click Create.
  
**Step 2: Add Selenium Dependency**

- Open the pom.xml file.
- Add the Selenium Java dependency.
- Save the file.
- Reload Maven to download the required libraries.
  
**Step 3: Create a Java Class**

- Go to src/test/java.
- Create a package named stepdefinition.
- Create a Java class named FirstTest.java.

**Step 4: Write Selenium Code**

- Import WebDriver and ChromeDriver.
- Create a WebDriver object.
- Launch Chrome browser.
- Open the practice website.
- Print the page title.
- Close the browser.

**Step 5: Execute the Program**

- Right-click the Java file.
- Select Run.
- Chrome browser opens automatically.
- Selenium navigates to the practice website.
- The page title appears in the console.
- Browser closes after execution.
  
**Step 6: Understand What I Learned**

- Selenium WebDriver: Controls a real browser using Java code.
- Maven: Manages project dependencies through pom.xml.
- Selenium Manager: Automatically manages the required browser driver, so manual ChromeDriver downloads are generally unnecessary.

**Expected Result**

- Maven project created.
- Selenium dependency added.
- Chrome browser launched.
- Practice website opened.
- Page title printed in the console.
- Browser closed successfully.

**Learning Outcome: Successfully configured Selenium WebDriver with Java and Maven and executed my first browser automation program.**
