package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MembershipsPage extends PageBase {

    WebDriver driver;

    public MembershipsPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    private final String MembershipAddBtn = ".oxd-button--secondary";

    private final String RecordExists = "//div[@class='oxd-form-row'] /div";
   private final String idRecordTextLevel = "//div[@class='oxd-form-row'] /div[1] /div[2] /input";
    private final String idMemSaveBtn = "//button[@type='submit']";
    private final String tblMembership = "//div[@class='orangehrm-container']";
    private final String getIdMemCancelBtn = "//div[@class='oxd-form-actions'] /button[1]";

    private final String getItemSelect = ".oxd-table-cell.oxd-padding-cell";
    private int userWeNeedToSelect = 2;
  @FindBy(css = ".oxd-table-card")
   private List<WebElement> memberships;



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
    public void memberSelect(int i) {
        List<WebElement> checkBoxList = driver.findElements(By.xpath("input[@type='checkbox']"));
        System.out.println(checkBoxList.size());
    }
}


    