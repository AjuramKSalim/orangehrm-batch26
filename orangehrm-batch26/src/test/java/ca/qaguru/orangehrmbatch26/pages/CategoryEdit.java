package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryEdit extends PageBase {

    WebDriver driver;
        public CategoryEdit (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String lblAlreadyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String cancelBtn = "//div[@class='oxd-form-actions'] /button[1]";
    private final String saveBtn = "//div[@class='oxd-form-actions'] /button[2]";
    private final String tblCategory_Job = ".oxd-table-body";
    private final String category_Jobs = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deleteBtn = ".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String category_JobEdit = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";

    @FindBy(xpath = category_JobEdit)
    private WebElement category_JobEditBox;

    @FindBy(css = editBtn)
    private List<WebElement> listEditBtn;

    @FindBy(css = deleteBtn)
    private List<WebElement> listDeleteBtn;
    @FindBy(xpath = category_Jobs)
    private List<WebElement> listCategory_Jobs;


    public void getCategory_JobNames() {

        List<String> txtCategory_Jobs = listCategory_Jobs.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println(" ");
        System.out.println("job categories are:\n");
        for (String txtCategory_Job : txtCategory_Jobs) {
            System.out.println(txtCategory_Job);
        }

    }

     public Boolean category_JobEdit(String category_Job, String category_JobNew) {
        Actions a = new Actions(driver);
        isElementVisible(By.cssSelector(tblCategory_Job));
        getCategory_JobNames();
        for (int i = 0; i < listCategory_Jobs.size(); i++) {
            String value = listCategory_Jobs.get(i).getText();
            if (value.contains(category_Job)) {
                int row = i;
                listEditBtn.get(row).click();
            }
        }
        isElementVisible(By.cssSelector(category_JobEdit));
        a.moveToElement(category_JobEditBox).doubleClick().click().sendKeys(Keys.BACK_SPACE, category_JobNew).build().perform();
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(cancelBtn));
        } else {
            click(By.xpath(saveBtn));
        }
        Boolean wait = isElementVisible(By.cssSelector(tblCategory_Job));
        return wait;
    }

    public void category_JobEditVerify(String category_Job, String category_JobNew) {
        System.out.println("\nCategories list after editing "+ category_Job);
        getCategory_JobNames();
        Boolean match = listCategory_Jobs.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(category_JobNew));
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println(category_Job + " is replaced with " + category_JobNew + " successfully");

    }
}





