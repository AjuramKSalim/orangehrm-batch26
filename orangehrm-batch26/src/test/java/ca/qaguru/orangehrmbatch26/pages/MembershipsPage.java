package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class MembershipsPage extends PageBase {

    WebDriver driver;

    public MembershipsPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    private final String MembershipAddBtn = ".oxd-button--secondary";
    private final String lblAlreadyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String RecordExists = "//div[@class='oxd-form-row'] /div";
   private final String idRecordTextLevel = "//div[@class='oxd-form-row'] /div[1] /div[2] /input";
    private final String idMemSaveBtn = "//button[@type='submit']";
    private final String tblMembership = "//div[@class='orangehrm-container']";
    private final String getIdMemCancelBtn = "//div[@class='oxd-form-actions'] /button[1]";

    private final String getItemSelect = ".oxd-table-cell.oxd-padding-cell";

    private final String tblmember = ".oxd-table-body";
    private final String memberdeletepop = "//button[text()=' Yes, Delete ']";
    private final String memberdeletepopcontainer =".orangehrm-dialog-popup";
private final String members ="//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
private final String memberdelete =".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String memberEditBtn = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";

  @FindBy(css = ".oxd-table-card")
   private List<WebElement> memberships;

    @FindBy(xpath = memberEditBtn)
    private WebElement memberEditBox;

    @FindBy(css = editBtn)
    private List<WebElement> EditBtn;

    @FindBy(css = memberdelete)
    private List<WebElement> memberdeletebutton;


    //locators
 /*   By membershiplink = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[5]/a");

    //actions
    public void navigateToMembership() {
        WebElement membership = getElement(membershiplink);
        membership.click();

    }*/

 /* private WebElement getElement(By membershiplink) {

        By locator = null;
        WebElement element = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }*/


   /* public void selectMenu(String membership) {
        //   click(By.cssSelector(MembershipAddBtn));

    }

    public void click() {
    }

    public void saveNewMembership(String level1) {
        click(By.id(idMemSaveBtn));
        setText(By.id(idRecordTextLevel), level1);
        click(By.id(getIdMemCancelBtn));
    }*/

    public boolean MembershipAddBtn(String membership)  {

        click(By.cssSelector(MembershipAddBtn));
        setText(By.xpath(idRecordTextLevel),membership);
        sleep(3000);
        if (getText(By.xpath(RecordExists)).contains("Already exists")) {
            click(By.xpath(getIdMemCancelBtn));
        } else {
            click(By.xpath(idMemSaveBtn));
        }
        boolean wait = isElementVisible(By.cssSelector(tblMembership));
        return wait;
    }



    public void memberVerify(String membership) {

int length= memberships.size();

        for(WebElement value:memberships)
        {
            String text= value.getText();
            System.out.println(text);
        }

    }
    @FindBy(xpath = members)
    private List<WebElement> memberlist;

    public void getMemberName() {

        List<String> Membername = memberlist.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println(" ");
        System.out.println("member list  in the table:\n");
        for (String Membertxt : Membername) {
            System.out.println(Membertxt);
        }

    }

    public Boolean memberEdit(String membership, String newMember) {
        Actions a = new Actions(driver);
        isElementVisible(By.cssSelector(tblmember));
        getMemberName();
        for (int i = 0; i < memberlist.size(); i++) {
            String value = memberlist.get(i).getText();
          /*  if (value.contains(membership)) {
                int row = i;
                memberEditBtn.get(row).click();
            }*/
        }
        isElementVisible(By.cssSelector(memberEditBtn));
        a.moveToElement(memberEditBox).doubleClick().click().sendKeys(Keys.BACK_SPACE, newMember).build().perform();
        if (getText(By.xpath(RecordExists)).contains("Already exists")) {
            click(By.xpath(getIdMemCancelBtn));
        } else {
            click(By.xpath(idMemSaveBtn));
        }
        Boolean wait = isElementVisible(By.cssSelector(tblmember));
        return wait;
    }
    public void memberEditedVerify(String membership, String newMember) {
        System.out.println("\nnew member list is displayed "+ membership);
        getMemberName();
        Boolean match = memberlist.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(newMember));
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println(membership + " is replaced with " + newMember + " successfully");



    }
    
    

    public Boolean memberDelete(String membership) {

        isElementVisible(By.cssSelector(tblmember));
        getMemberName();
        for (int i = 0; i < memberlist.size(); i++) {
            String value = memberlist.get(i).getText();
          /*  if (value.contains(membership)) {
                int row = i;
                memberdelete.get(row).click();
                isElementVisible(By.cssSelector(memberdeletepopcontainer));
                click(By.xpath(memberdeletepop));
            }*/
        }
        Boolean wait = isElementVisible(By.cssSelector(tblmember));
        return wait;
    }

    public void memberDeleteVerify(String membership) {
        System.out.println("\nmember list shows after delete  "+ membership);
        getMemberName();
        Boolean match = memberlist.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(membership));
        Assert.assertFalse(match);
        System.out.println(" ");
        System.out.println(membership + " : " + " is deleted successfully");

    }
}

    /*package ca.qaguru.orangehrmbatch26.pages;

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
}*/
