package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import page.Lotuseyeclass;
import utils.ExcelUtils;

public class Lotuseyetest extends Baseclass {

    @Test
    public void testAppointment() throws Exception {
        // Path to the Excel file
        String excelPath = "D://lotusxl.xlsx"; 
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");

        // Get total number of rows
        int rowCount = excel.getRowCount();

        // Loop through each row, starting from 1 to skip the header
        for (int i = 1; i < rowCount; i++) {
            String name = excel.getCellData(i, 0); // Name
            String phno = excel.getCellData(i, 1); // Phone number
            String city = excel.getCellData(i, 2); // City
            String description = excel.getCellData(i, 3); // Description
            String branch = excel.getCellData(i, 4); // Branch dropdown value
            String doctor = excel.getCellData(i, 5); // Doctor dropdown value

            // Skip the row if mandatory fields are empty
            if (name.isEmpty() || phno.isEmpty() || city.isEmpty() || branch.isEmpty() || doctor.isEmpty()) {
                System.out.println("Skipping row " + i + " due to empty fields.");
                continue; // Move to the next iteration if any of the mandatory fields are empty
            }

            // Initialize the page object
            Lotuseyeclass le = new Lotuseyeclass(driver);

            // Fill in the form using the data from the Excel sheet
            le.appointment(name, phno, city, description);

            // Handle the dropdowns dynamically from the Excel sheet
            try {
                le.dropdownbranch(branch); // Branch from Excel
                le.dropdowndoctor(doctor); // Doctor from Excel
            } catch (UnsupportedOperationException e) {
                System.out.println("Failed to select a disabled option: " + e.getMessage());
                continue; // Skip this iteration and move to the next row
            }

            // Select the date
            le.datepicker("September 2024");

            // Submit the form
            le.submitbutton();

            // Add a wait or page reload to ensure the test moves to the next record cleanly
            Thread.sleep(4000); // Adjust wait based on application behavior

            // Click "Home" link/button after submission
            driver.findElement(By.xpath("//*[@id='homelink']/button")).click(); 
            Thread.sleep(2000); // Adjust wait based on application behavior

            // Navigate back to the appointment form page
            driver.findElement(By.xpath("//*[@id='navbarNavDropdown']/ul/li[6]/a/button")).click();
        }

        // Close the Excel workbook
        excel.closeWorkbook();
    }
}
