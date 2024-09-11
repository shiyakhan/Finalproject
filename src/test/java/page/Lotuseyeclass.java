package page;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lotuseyeclass {
    
    WebDriver driver;
    
    By name = By.xpath("//*[@id='exampleInputName']");
    By phno = By.xpath("//*[@id='exampleInputPhno']");
    By city = By.xpath("//*[@id='exampleInputcity']");
    By branch = By.xpath("//*[@id='selectinputbox1']");
    By doctor = By.xpath("//*[@id='selectinputbox2']");
    By date = By.xpath("//*[@id='slotvalidfrom']");
    By description = By.xpath("//*[@id='exampleInputMsg']");
    By submit = By.xpath("//*[@id='Requestappointmentform']/div[5]/div/button");
    
    public Lotuseyeclass(WebDriver driver) {
        this.driver = driver;
    }
    
    // Fill appointment details
    public void appointment(String name1, String phno1, String city1, String description1) {
        driver.findElement(name).sendKeys(name1);
        driver.findElement(phno).sendKeys(phno1);
        driver.findElement(city).sendKeys(city1);
        driver.findElement(description).sendKeys(description1);
    }

    // Select dropdown for doctor dynamically
    public void dropdowndoctor(String doctorValue) {
        WebElement dropDoctor = driver.findElement(doctor);
        Select drop2 = new Select(dropDoctor);
        drop2.selectByVisibleText(doctorValue);
    }

    // Select dropdown for branch dynamically
    public void dropdownbranch(String branchValue) {
        WebElement dropBranch = driver.findElement(branch);
        Select drop1 = new Select(dropBranch);
        drop1.selectByVisibleText(branchValue);        
    }

    // Date picker logic
    public void datepicker(String month) throws InterruptedException {
        WebElement dateElement = driver.findElement(date);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dateElement));
        
        dateElement.click();
        String date = "2"; // Select 2nd day as example

        while (true) {
            String actual = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div")).getText();
            
            if (actual.equals(month)) {
                break;
            } else {
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a")).click();
            }
            Thread.sleep(2000);
        }
        
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]")).click();
    }

    // Submit form
    public void submitbutton() {
        driver.findElement(submit).submit();
    }
}
