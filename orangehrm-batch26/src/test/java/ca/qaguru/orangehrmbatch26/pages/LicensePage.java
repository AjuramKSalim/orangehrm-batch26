package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LicensePage extends PageBase {

    WebDriver driver;

    public LicensePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String licenseAddBtn = ".oxd-button--secondary";
    private final String txtBoxLicense = "div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String lblAlreadyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String cancelBtn = "//div[@class='oxd-form-actions'] /button[1]";
    private final String saveBtn = "//div[@class='oxd-form-actions'] /button[2]";
    private final String tblLicense = ".oxd-table-body";
    @FindBy(xpath = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']")
    private List<WebElement> licenses;


    public boolean licenseAdd(String license) {
        click(By.cssSelector(licenseAddBtn));
        setText(By.cssSelector(txtBoxLicense), license);
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(cancelBtn));
        } else {
            click(By.xpath(saveBtn));
        }
        boolean wait = isElementVisible(By.cssSelector(tblLicense));
        return wait;
    }

    public void licenseVerify(String license) {

        List<String> values = licenses.stream().map(s -> s.getText()).collect(Collectors.toList());
        for (String value : values) {
            System.out.println(value);
        }
        Boolean match = values.stream().anyMatch(s -> s.equalsIgnoreCase(license));
        System.out.println(" ");
        System.out.println(license+ " : " + " is added successfully");
        Assert.assertTrue(match);


    }
}



