package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class LicenseNewTest extends TestBase {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\Automated testing\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String License="New License Anjana";
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul /li[4] /span")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Licenses']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".oxd-button--secondary")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div[class='oxd-form-row'] input[class*='oxd-input']")).sendKeys(License);
        Thread.sleep(3000);
        if(driver.findElement(By.xpath("//div[@class='oxd-form-row'] /div")).getText().contains("Already exists"))
        {
            driver.findElement(By.xpath("//div[@class='oxd-form-actions'] /button[1]")).click();
        }
        else
        {
            driver.findElement(By.xpath("//div[@class='oxd-form-actions'] /button[2]")).click();
        }

        Thread.sleep(3000);
        List<WebElement> values = driver.findElements(By.xpath("//div[@class='oxd-table-body'] /div /div /div[2] /div"));


        for (WebElement value : values) {

            String licenseName=value.getText();
            if(licenseName.equalsIgnoreCase(License))
            {
                Assert.assertTrue(true);

                System.out.println(License + " added successfully");


            }

        }

        driver.quit();


    }

}



