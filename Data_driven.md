# Lesson 14 – Data-Driven Tests

## Goal
Run the same test with 10 data sets from Excel, CSV or JSON using a data provider or parametrize.

## What is data-driven testing?
Data-driven testing means **one test, many inputs**. Instead of writing a separate test for every username/password combination, you write **one test method** and feed it different data sets. The test runs once for each data set.

In this lesson the data sets live in an **Excel file** (`src/test/resources/Testdata/Logindata.xlsx`, sheet `Logindata`) — 10 SauceDemo login combinations. A TestNG **@DataProvider** reads the Excel file (using **Apache POI**) and gives each row to the test.

**Why data-driven?**
- **Less code** — 1 test method instead of 10 test methods.
- **Easy to add cases** — add a row to the Excel file, no code change.
- **Data separate from code** — anyone can edit the Excel file without touching Java.
- **Better coverage** — valid users, locked-out user, wrong password, empty fields — all in one test.

## Files in this lesson

| File | What it is |
|---|---|
| `src/test/resources/Testdata/Logindata.xlsx` | The 10 data sets (testCase, username, password, expected) |
| `utils/ExcelUtils.java` | Reads the Excel file with Apache POI and returns the rows |
| `testcases/LoginTest.java` | The test — 1 test method + @DataProvider that reads the Excel file |

**Practice site:** SauceDemo – https://www.saucedemo.com/

## Steps

### Step 1 – Add the Apache POI dependency to pom.xml
Apache POI is the library that reads Excel files in Java. Add it to the `<dependencies>` section of `pom.xml` (Selenium 4.25.0 and TestNG 7.8.0 are already there):

```xml
<!-- Apache POI for Excel -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.4.1</version>
</dependency>

Then reload Maven (Maven panel → Reload All Maven Projects) so the library downloads.

Step 2 – Create the Excel file with 10 data sets
Create the folder src/test/resources/Testdata and the file Logindata.xlsx inside it. Sheet name: Logindata. Row 1 is the header; rows 2–11 are the 10 data sets. The expected column uses keywords: success, locked, error:

TestCase	Username	Password	Expected
TC01	standard_user	secret_sauce	success
TC02	standard_user	wrong123	error
TC03	wrong_user	secret_sauce	error
TC04	(blank)	secret_sauce	error
TC05	standard_user	(blank)	error
TC06	wrong_user	wrong123	error
TC07	locked_out_user	secret_sauce	locked
TC08	(blank)	(blank)	error
TC09	standard_user	secret_sauce123	error
TC10	problem_user	secret_sauce	success
For the empty username/password rows, leave the cell blank (no space).

Step 3 – Create the ExcelUtils class (utils package)
ExcelUtils opens the Excel file with Apache POI and returns every data row as Object[][] — 4 columns: testCase, username, password, expected:

java
public static Object[][] getTestData(String filePath, String sheetName) throws Exception {

    FileInputStream fis = new FileInputStream(filePath);              // open the Excel file
    Workbook workbook = new XSSFWorkbook(fis);                        // load the .xlsx file
    Sheet sheet = workbook.getSheet(sheetName);                       // get the sheet by name

    int rowCount = sheet.getLastRowNum();                             // number of data rows (header is row 0)
    Object[][] data = new Object[rowCount][4];                        // 4 columns: testCase, username, password, expected

    for (int i = 1; i <= rowCount; i++) {
        Row row = sheet.getRow(i);                                    
        data[i - 1][0] = getCellValue(row.getCell(0));                // column A: testCase
        data[i - 1][1] = getCellValue(row.getCell(1));                // column B: username
        data[i - 1][2] = getCellValue(row.getCell(2));                // column C: password
        data[i - 1][3] = getCellValue(row.getCell(3));                // column D: expected result
    }

    workbook.close();                                                 // close the workbook
    fis.close();                                                      // close the file

    return data;                                                      // give the rows to the test
}
A blank cell returns "" (empty text) — that's how the empty username/password rows work.

Step 4 – Write one test that uses the data provider (testcases package)
The @DataProvider calls ExcelUtils, and the test receives one Excel row at a time (testCase, username, password, expected) and runs 10 times. The expected keyword decides what to check:

java
@DataProvider(name = "loginData")
public Object[][] getLogindata() throws Exception {
    return ExcelUtils.getTestData("src/test/resources/Testdata/Logindata.xlsx", "Logindata");
}

@Test(dataProvider = "loginData")
public void loginTest(
        String testCase,
        String username,
        String password,
        String expected) {

    System.out.println("Executing Test Case: " + testCase);

    driver.findElement(By.id("user-name")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("login-button")).click();

    if (expected.equalsIgnoreCase("success")) {
        // check the Products page title
    } else if (expected.equalsIgnoreCase("locked")) {
        // check the error contains "locked out"
    } else if (expected.equalsIgnoreCase("error")) {
        // check an error message is shown
    }
}
@BeforeMethod / @AfterMethod give every data row a fresh browser, so the 10 runs don't affect each other.

What I learned
Data-driven testing = one test, many inputs — the test runs once per data set.
TestNG @DataProvider feeds the data rows to the test method. The provider name in @DataProvider(name = ...) must match dataProvider = ... in @Test exactly (case-sensitive).
Apache POI reads Excel files in Java (XSSFWorkbook for .xlsx).
The data lives in an Excel file, separate from the code — add a row to add a test case.
A blank cell in Excel becomes "" (empty text) in the test.
The number of columns the provider returns must match the number of parameters in the test method.
The expected result is part of the data — here a keyword (success / locked / error) that tells the test what to check.
