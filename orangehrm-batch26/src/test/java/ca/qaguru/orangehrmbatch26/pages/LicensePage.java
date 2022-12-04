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
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private final String licenseBtnAdd=".oxd-button--secondary";
    private final String licenseNameBox="div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String alreadyExistsMessage="//div[@class='oxd-form-row'] /div";
    private final String cancelBtn="//div[@class='oxd-form-actions'] /button[1]";
    private final String saveBtn="//div[@class='oxd-form-actions'] /button[2]";
   // private final  String licenseContainer=".orangehrm-background-container";

    @FindBy(xpath="//div[@class='oxd-table-body'] /div /div /div[2] /div")
    private List<WebElement> licenses;



    public void licenseAdd(String License) throws InterruptedException {
        click(By.cssSelector(licenseBtnAdd));
        setText(By.cssSelector(licenseNameBox), License);
        if (getText(By.xpath(alreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(cancelBtn));
        } else {
            click(By.xpath(saveBtn));
        }
        Thread.sleep(3000);
    }

        public void licenseVerify(String License)  {

            //licenses.stream().map(s->s.getText()).forEach(s-> System.out.println(s));
            List<String> values=licenses.stream().map(s->s.getText()).collect(Collectors.toList());
            for (String value:values)
            {
                System.out.println(value);
            }
            Boolean match= values.stream().anyMatch(s->s.equalsIgnoreCase(License));
            Assert.assertTrue(match);


         }
   }



