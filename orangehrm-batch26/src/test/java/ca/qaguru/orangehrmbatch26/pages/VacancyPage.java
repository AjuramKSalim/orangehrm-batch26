package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.stream.Collectors;

public class VacancyPage extends PageBase {
    WebDriver driver;
    private final String ddlJobTitle ="//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[1]/descendant::div[@class='oxd-select-text-input']";
    private final String ddlSelect="//*[text()='-- Select --']";
    private final String ddlVacancy ="//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[2]/descendant::div[@class='oxd-select-text-input']";
    private final String ddlHRManager ="//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[3]/descendant::div[@class='oxd-select-text-input']";
    private final String ddlStatus ="//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[4]/descendant::div[@class='oxd-select-text-input']";
    private final String BtnSearch="//button[@type='submit']";
    private final String lblRecords ="//div[@class='orangehrm-container']/preceding::span[@class='oxd-text oxd-text--span']";
    @FindBy(xpath ="//div[@class='oxd-table-body']/descendant::div[@class='oxd-table-cell oxd-padding-cell'][2]/div")
    private  List<WebElement> lstVacancy ;


    public VacancyPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void searchVacancy(String jobTitle,String vacancy, String HrManager ,String status){
        click(By.xpath(ddlJobTitle));
        click(By.xpath(ddlSelect.replace("-- Select --",jobTitle)));
        click(By.xpath(ddlVacancy));
        click(By.xpath(ddlSelect.replace("-- Select --",vacancy)));
        click(By.xpath(ddlHRManager));
        click(By.xpath(ddlSelect.replace("-- Select --",HrManager)));
        click(By.xpath(ddlStatus));
        click(By.xpath(ddlSelect.replace("-- Select --",status)));
        click(By.xpath(BtnSearch));
        if(isElementPresent(By.xpath(lblRecords))) {
            List<String> vacancies = lstVacancy.stream().map(s -> s.getText()).collect(Collectors.toList());
            System.out.println("Number of vacancies is " + vacancies.size());
        }


    }
}
