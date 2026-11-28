
package utils;

import org.apache.poi.ss.usermodel.Cell;                                 // one cell in the Excel sheet
import org.apache.poi.ss.usermodel.Row;                                  // one row in the Excel sheet
import org.apache.poi.ss.usermodel.Sheet;                                // one sheet (tab) in the Excel file
import org.apache.poi.ss.usermodel.Workbook;                             // the whole Excel file
import org.apache.poi.xssf.usermodel.XSSFWorkbook;                       // opens .xlsx files

import java.io.FileInputStream;

public class ExcelUtils {

    // ===== Read all data rows from the Excel file and return them as Object[][] =====
    public static Object[][] getTestData(String filePath, String sheetName) throws Exception {

        FileInputStream fis = new FileInputStream(filePath);              // open the Excel file
        Workbook workbook = new XSSFWorkbook(fis);                        // load the .xlsx file
        Sheet sheet = workbook.getSheet(sheetName);                       // get the sheet by name

        int rowCount = sheet.getLastRowNum();                             // number of data rows (header is row 0)
        Object[][] data = new Object[rowCount][4];                        // 3 columns: username, password, expected

        for (int i = 1; i <= rowCount; i++) {                             // start from row 1 (skip the header row)
            Row row = sheet.getRow(i);                                    // get the row
            data[i - 1][0] = getCellValue(row.getCell(0));                // column A: username
            data[i - 1][1] = getCellValue(row.getCell(1));                // column B: password
            data[i - 1][2] = getCellValue(row.getCell(2));
            data[i - 1][3] = getCellValue(row.getCell(3));                  // column C: expected result
        }

        workbook.close();                                                 // close the workbook
        fis.close();                                                      // close the file

        return data;                                                      // give the rows to the test
    }

    // ===== Read one cell as text (empty cell = empty string) =====
    private static String getCellValue(Cell cell) {
        if (cell == null) {                                               // blank cell
            return "";                                                    // return empty text
        }
        return cell.toString();                                           // return the cell text
    }
}
