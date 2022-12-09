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

public class EducationPage extends PageBase {
    WebDriver driver;
    private final String idEduAddBtn = ".oxd-button--secondary";
    private final String idEduTxtLevel="div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String idEduSaveBtn="//button[@type='submit']";
    private final String lblAlreadyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String getIdEduCancelBtn= "//div[@class='oxd-form-actions'] /button[1]";
    private final String tblEducation = ".oxd-table-body";
    private final String educations = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    @FindBy(xpath = educations)
    private List<WebElement> listEducations;

    public EducationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean saveNewEducation(String educationName) {
        click(By.cssSelector(idEduAddBtn));
        setText(By.cssSelector(idEduTxtLevel), educationName);
        if (getText(By.xpath(lblAlreadyExistsMessage)).contains("Already exists")) {
            click(By.xpath(getIdEduCancelBtn));
        } else {
            click(By.xpath( idEduSaveBtn));
        }
        boolean wait = isElementVisible(By.cssSelector(tblEducation));
        return wait;
    }

    public void educationVerify(String educationName)
    {

        for (WebElement Education : listEducations) {
            String  txtEducation=Education.getText();
            System.out.println(txtEducation);

        }
        Boolean match=listEducations.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(educationName));
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println(educationName+" is added successfully");
    }
}
