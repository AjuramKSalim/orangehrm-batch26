package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MembershipsPage extends PageBase {
    public MembershipsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

  //  @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[5]/a")
 //   private List<WebElement> Memberships;
}
