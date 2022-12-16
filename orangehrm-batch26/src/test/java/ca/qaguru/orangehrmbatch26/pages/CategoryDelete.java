package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDelete extends PageBase {

    WebDriver driver;

    public CategoryDelete (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String tblCategory_Job = ".oxd-table-body";
    private final String deletePopup = "//button[text()=' Yes, Delete ']";
    private final String deletePopupContainer = ".orangehrm-dialog-popup";
    private final String category_Jobs = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deleteBtn = ".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String category_JobEdit = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";
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
    public Boolean category_JobDelete(String category_Job) {

        isElementVisible(By.cssSelector(tblCategory_Job));
        getCategory_JobNames();
        for (int i = 0; i < listCategory_Jobs.size(); i++) {
            String value = listCategory_Jobs.get(i).getText();
            if (value.contains(category_Job)) {
                int row = i;
                listDeleteBtn.get(row).click();
                isElementVisible(By.cssSelector(deletePopupContainer));
                click(By.xpath(deletePopup));
            }
        }
        Boolean wait = isElementVisible(By.cssSelector(tblCategory_Job));
        return wait;
    }

    public void category_JobDeleteVerify(String category_Job) {
        System.out.println("\nNew Jov categories are: "+ category_Job);
        getCategory_JobNames();
        Boolean match = listCategory_Jobs.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(category_Job));
        Assert.assertFalse(match);
        System.out.println(" ");
        System.out.println(category_Job + " : " + " deleted successfully");

    }
}










