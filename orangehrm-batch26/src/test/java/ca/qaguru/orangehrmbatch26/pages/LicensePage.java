package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LicensePage extends PageBase {

    private final String licenseAdd =".oxd-button--secondary" ;
    private final String licenseName="div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String licenseSave="button[class*='oxd-button--secondary']";

    public LicensePage(WebDriver driver) {super(driver);
    }

    public void addNewLicense(String newLicense)
    {
        click(By.cssSelector(licenseAdd));
        setText(By.cssSelector(licenseName), newLicense);
        click(By.cssSelector(licenseSave));

    }
}
