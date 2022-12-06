package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MembershipsPage extends PageBase {

    private final String MembershipAddBtn = "oxd-button oxd-button--medium oxd-button--secondary";
 ;
    private final String idRecordTextLevel="Membership_record";
    private final String idMemSaveBtn="btnSave";
    private final String getIdMemCancelBtn= "btnCancel";
    public MembershipsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[5]/a")
   private List<WebElement> Memberships;

    //locators
    By membershiplink = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[5]/a");

//actions
    public void navigateToMembership(){
        WebElement membership = getElement(membershiplink);
        membership.click();

    }

    private WebElement getElement(By membershiplink) {

        By locator = null;
        WebElement element = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public void selectMenu(String memberships) {
     //   click(By.cssSelector(MembershipAddBtn));

    }

    public void clicklogin() {
    }
}
