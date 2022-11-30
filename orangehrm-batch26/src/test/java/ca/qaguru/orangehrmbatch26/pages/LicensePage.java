package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class LicensePage extends PageBase {

    private final String licenseAdd = ".oxd-button--secondary";
    private final String licenseName = "div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String licenseSave = "button[class*='oxd-button--secondary']";

    //private  final  String licenseCancel=".oxd-form-actions button[type='button']";


    public LicensePage(WebDriver driver) {
        super(driver);
    }
    String newLicense;
    public void licenseNewAdd(String newLicense) {
        click(By.cssSelector(licenseAdd));
        setText(By.cssSelector(licenseName), newLicense);
        click(By.cssSelector(licenseSave));

    }

    public void newLicenseVerify() {
       isElementVisible(By.className("orangehrm-container"));

        List<WebElement> values = driver.findElements(By.xpath("//div[@class='oxd-table-body'] /div /div /div[2] /div"));

        for (WebElement value : values) {

            System.out.println(value.getText());
            }
        }
    }


