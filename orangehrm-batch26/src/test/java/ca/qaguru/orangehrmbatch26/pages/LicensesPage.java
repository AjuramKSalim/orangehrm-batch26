package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LicensesPage extends PageBase {

    WebDriver driver;

    public LicensesPage(WebDriver driver) {
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
    private final String deletePopup = "//button[text()=' Yes, Delete ']";
    private final String deletePopupContainer = ".orangehrm-dialog-popup";
    private final String licenses = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deleteBtn = ".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String licenseEdit = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";

    @FindBy(xpath = licenseEdit)
    private WebElement licenseEditBox;

    @FindBy(css = editBtn)
    private List<WebElement> listEditBtn;

    @FindBy(css = deleteBtn)
    private List<WebElement> listDeleteBtn;
    @FindBy(xpath = licenses)
    private List<WebElement> listLicenses;


    public void getLicenseNames() {

        List<String> txtLicenses = listLicenses.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println(" ");
        System.out.println("licenses are:\n");
        for (String txtLicense : txtLicenses) {
            System.out.println(txtLicense);
        }

    }

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


    public void licenseAddVerify(String license) {
        getLicenseNames();
        Boolean match = listLicenses.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(license));
        Assert.assertTrue(match);
        System.out.println(" ");
        System.out.println(license + " : " + " is added successfully");

    }

    public Boolean licenseEdit(String license, String licenseNew) {
        Actions a = new Actions(driver);
        isElementVisible(By.cssSelector(tblLicense));
        getLicenseNames();
        for (int i = 0; i < listLicenses.size(); i++) {
            String value = listLicenses.get(i).getText();
            if (value.contains(license)) {
                int row = i;
                listEditBtn.get(row).click();
            }
        }
        isElementVisible(By.cssSelector(licenseEdit));
        a.moveToElement(licenseEditBox).doubleClick().click().sendKeys(Keys.BACK_SPACE, licenseNew).build().perform();
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(cancelBtn));
        } else {
            click(By.xpath(saveBtn));
        }
        Boolean wait = isElementVisible(By.cssSelector(tblLicense));
        return wait;
    }

    public void licenseEditVerify(String license, String licenseNew) {
        System.out.println("\nLicense list after editing "+ license);
        getLicenseNames();
        Boolean match = listLicenses.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(licenseNew));
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println(license + " is replaced with " + licenseNew + " successfully");

    }

    public Boolean licenseDelete(String license) {

        isElementVisible(By.cssSelector(tblLicense));
        getLicenseNames();
        for (int i = 0; i < listLicenses.size(); i++) {
            String value = listLicenses.get(i).getText();
            if (value.contains(license)) {
                int row = i;
                listDeleteBtn.get(row).click();
                isElementVisible(By.cssSelector(deletePopupContainer));
                click(By.xpath(deletePopup));
            }
        }
        Boolean wait = isElementVisible(By.cssSelector(tblLicense));
        return wait;
    }

    public void licenseDeleteVerify(String license) {
        System.out.println("\nLicense list after deleting "+ license);
        getLicenseNames();
        Boolean match = listLicenses.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(license));
        Assert.assertFalse(match);
        System.out.println(" ");
        System.out.println(license + " : " + " is deleted successfully");

    }
}





