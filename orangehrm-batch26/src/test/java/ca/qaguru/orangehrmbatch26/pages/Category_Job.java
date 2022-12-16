package ca.qaguru.orangehrmbatch26.pages;

        import ca.qaguru.orangehrmbatch26.library.PageBase;
        import org.openqa.selenium.*;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.testng.Assert;

        import java.util.List;
        import java.util.stream.Collectors;

public class Category_Job extends PageBase {

    WebDriver driver;

    public Category_Job (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String category_JobAddBtn = ".oxd-button--secondary";
    private final String txtBoxCategory_Job = "div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String lblAlreadyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String cancelBtn = "//div[@class='oxd-form-actions'] /button[1]";
    private final String saveBtn = "//div[@class='oxd-form-actions'] /button[2]";
    private final String tblCategory_Job = ".oxd-table-body";
    private final String category_Jobs = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deleteBtn = ".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String category_JobEdit = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";
    @FindBy(xpath = category_Jobs)
    private List<WebElement> listCategory_Jobs;

    public void getCategory_JobNames() {

        List<String> txtCategory_Jobs = listCategory_Jobs.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println(" ");
        System.out.println("The Job categories are:\n");
        for (String txtCategory_Job : txtCategory_Jobs) {
            System.out.println(txtCategory_Job);
        }
    }
    public boolean Category_JobAdd(String category_Job) {
        click(By.cssSelector(category_JobAddBtn));
        setText(By.cssSelector(txtBoxCategory_Job), category_Job);
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(cancelBtn));
        } else {
            click(By.xpath(saveBtn));
        }
        boolean wait = isElementVisible(By.cssSelector(tblCategory_Job));
        return wait;
    }
    public void category_JobAddVerify(String category_Job) {
        getCategory_JobNames();
        Boolean match = listCategory_Jobs.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(category_Job));
        Assert.assertTrue(match);
        System.out.println(" ");
        System.out.println(category_Job + " : " + " is added successfully");

    }
  }





