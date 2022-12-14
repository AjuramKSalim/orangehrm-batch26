package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class UserManagementPage  extends  PageBase{

    WebDriver driver;

    public UserManagementPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String userAddBtn = "//div[@class='orangehrm-header-container'] //descendant::button";
    private final String userAddPage = "//h6[text()='Add User']";
    private final String selectUserRole = "//label[text()='User Role'] //following::div[@class='oxd-select-text oxd-select-text--active'][1]";
    private final String dropDown = "//div[@class='oxd-select-dropdown --positon-bottom'] //child::span[text()='XXX']";
    private final String selectUserStatus = "//label[text()='User Role'] //following::div[@class='oxd-select-text oxd-select-text--active'][2]";
    private final String txtBoxEmployeeName = ".oxd-autocomplete-text-input";
    private final String employeeNames = "//div[@class='oxd-autocomplete-dropdown --positon-bottom'] //descendant::span";
    private final String txtBoxUsername = "//label[text()='Username'] //following::input[1]";
    private final String lblAlreadyExistsMessage = "//label[text()='Username'] //ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']";
    private final String lblErrorMessagePassword = "//div[@class='oxd-form-row user-password-row']";
    private final String lblInvalidMessage = "//label[text()='Employee Name'] //ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']";
    private final String cancelBtn = "//div[@class='oxd-form-actions'] /child::button[1]";
    private final String txtBoxPassword = "//label[text()='Password'] //following::input[1]";
    private final String txtBoxConfirmPassword = "//label[text()='Password'] //following::input[2]";
    private final String saveBtn = "//div[@class='oxd-form-actions'] /child::button[2]";
    private final String searchBtn = "//div[@class='oxd-form-actions'] /child::button[2]";
    private final String userNameRecords = "//div[@class='oxd-table-card'] //child::div[@class='oxd-table-cell oxd-padding-cell'][2]";
    private final String userRecords = "//div[@class='oxd-table-card']";
    private final String deleteBtn = "//div[@class='oxd-table-cell-actions'] /child::button[1]";
    private final String deletePopup = "//button[text()=' Yes, Delete ']";
    private final String deletePopupContainer = ".orangehrm-dialog-popup";
    private final String dropDownLogout = "//span[@class='oxd-userdropdown-tab']";
    private final String dropDownBtn = "//a[text()='Logout']";
    private final String searchUsernameBox = "//div[@class='oxd-form-row']//child::input[@class='oxd-input oxd-input--active']";

    @FindBy(css = txtBoxEmployeeName)
    private WebElement employeeNameBox;
    @FindBy(xpath = employeeNames)
    private List<WebElement> listEmployeeNames;
    @FindBy(xpath = userNameRecords)
    private List<WebElement> listUserNameRecords;
    @FindBy(xpath = userRecords)
    private List<WebElement> listUserRecords;
    @FindBy(xpath = deleteBtn)
    private List<WebElement> listDeleteBtn;

    public void addUser(String role, String status, String employeeName, String userName, String password) {
        Actions a = new Actions(driver);
        click(By.xpath(userAddBtn));
        isElementVisible(By.xpath(userAddPage));
        click(By.xpath(selectUserRole));
        click(By.xpath(dropDown.replace("XXX", role)));
        click(By.xpath(selectUserStatus));
        click(By.xpath(dropDown.replace("XXX", status)));
        a.moveToElement(employeeNameBox).click().sendKeys(employeeName).build().perform();
        isElementVisible(By.xpath(employeeNames));
        for (WebElement name : listEmployeeNames) {
            if (name.getText().equalsIgnoreCase(employeeName)) {
                name.click();
                break;
            }
        }
        setText(By.xpath(txtBoxUsername), userName);
        setText(By.xpath(txtBoxPassword), password);
        setText(By.xpath(txtBoxConfirmPassword), password);
        sleep(3000);
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")
                || getText(By.xpath(lblInvalidMessage)).contains("Invalid")
                || !getText(By.xpath(lblErrorMessagePassword)).contains("Strongest")) {

            click(By.xpath(cancelBtn));
            isElementVisible(By.xpath(userNameRecords));
            System.out.println("Unable to add the provided information"+"\n"+"The username,password or employee name was not entered correctly");

        } else {


            click(By.xpath(saveBtn));
            isElementVisible(By.xpath(userNameRecords));
            getUserRecords(role);
            System.out.println("\n" + "Usernames after adding" + " " + userName + "\n");
            listUserNameRecords.stream().map(s -> s.getText()).forEach(s -> System.out.println(s));
            Boolean match = listUserNameRecords.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(userName));
            Assert.assertTrue(match);
            System.out.println("\n" + userName + " " + "is added successfully");

        }
    }

    public void getUserRecords(String role) {
        click(By.xpath(selectUserRole));
        click(By.xpath(dropDown.replace("XXX", role)));
        click(By.xpath(searchBtn));
        isElementVisible(By.xpath(userRecords));
    }

    public void deleteUser(String role, String userName) {
        isElementVisible(By.xpath(userRecords));
        getUserRecords(role);
        if(!listUserNameRecords.stream().map(s->s.getText()).anyMatch(s->s.equalsIgnoreCase(userName)))
        {
            System.out.println("\n"+ userName+" "+ "was not found and hence deletion not executed");
        }
        for (int i = 0; i < listUserRecords.size(); i++) {
            String userRecord = listUserRecords.get(i).getText();
            if (userRecord.contains(userName))  {
                int row = i;
                System.out.println("\n" + "Usernames before deleting" + " " + userName + "\n");
                listUserNameRecords.stream().map(s -> s.getText()).forEach(s -> System.out.println(s));
                listDeleteBtn.get(row).click();
                isElementVisible(By.cssSelector(deletePopupContainer));
                click(By.xpath(deletePopup));
                isElementVisible(By.xpath(userNameRecords));
                System.out.println("\n" + "Usernames after deleting" + " " + userName + "\n");
                listUserNameRecords.stream().map(s -> s.getText()).forEach(s -> System.out.println(s));
                Boolean match = listUserNameRecords.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(userName));
                Assert.assertFalse(match);
                System.out.println("\n" + userName + " " + "is deleted successfully");
                break;
            }

        }


    }


    }




