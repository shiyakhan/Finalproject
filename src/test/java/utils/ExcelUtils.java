package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String excelPath, String sheetName) {
        try (FileInputStream fileInputStream = new FileInputStream(excelPath)) {
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the row count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get the data from the Excel sheet
    public String getCellData(int rowNumber, int columnNumber) {
        Row row = sheet.getRow(rowNumber);
        DataFormatter formatter = new DataFormatter(); // Formats cells as strings
        return formatter.formatCellValue(row.getCell(columnNumber));
    }
    
    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
