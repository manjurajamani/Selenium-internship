# Lesson 14 – Data-Driven Tests

## Goal
Run the same test with 10 data sets from Excel using a data provider.

## What is data-driven testing?
One test, many inputs. Instead of writing 10 separate tests, I wrote **one test method** and fed it 10 rows of data from an Excel file. The test runs once for each row.

## Files
- `Logindata.xlsx` — the 10 data sets (testCase, username, password, expected)
- `ExcelUtils.java` — reads the Excel file (Apache POI)
- `LoginTest.java` — the test + @DataProvider

**Site:** SauceDemo – https://www.saucedemo.com/

## Steps

**Step 1 – Add Apache POI to pom.xml**
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.4.1</version>
</dependency>


Then reload Maven.

Step 2 – Create the Excel file
src/test/resources/Testdata/Logindata.xlsx, sheet Logindata. Header row + 10 data rows. Expected column: success, locked, or error. Leave the cell blank for empty username/password.

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


Step 3 – ExcelUtils reads the file
ExcelUtils.getTestData(...) opens the Excel file and returns all rows as Object[][] (4 columns).

Step 4 – One test uses the data provider

@DataProvider(name = "loginData")
public Object[][] getLogindata() throws Exception {
    return ExcelUtils.getTestData("src/test/resources/Testdata/Logindata.xlsx", "Logindata");
}

@Test(dataProvider = "loginData")
public void loginTest(String testCase, String username, String password, String expected) {
    // login, then check based on expected: success / locked / error
}

The test runs 10 times — once per Excel row.

What I learned

Data-driven testing = one test, many inputs.
@DataProvider feeds the rows to the test. The provider name must match exactly (case-sensitive).
Apache POI reads Excel files in Java.
Data stays in the Excel file — add a row to add a test case.
A blank cell becomes "" (empty text).
Columns from the provider must match the test's parameters.
