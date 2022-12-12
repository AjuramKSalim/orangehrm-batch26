package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class SkillsPage extends PageBase {

    WebDriver driver;
    private final String idSkillsAddBtn = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']";
    private final String idESkillName="//form[@class='oxd-form']/div[1]/div/div[2]/input";
    @FindBy(xpath = idESkillName)
    WebElement skillName;
    private final String idSkillDesc="//form[@class='oxd-form']/div[2]/div/div[2]/textarea";
    @FindBy(xpath = idSkillDesc)
    WebElement skillDesc;
    private final String idSkillSaveBtn="//button[@type='submit']";
    private final String idSkillExists ="//form[@class='oxd-form']/descendant::span";
    private final String idSkillCancelBtn= "//div[@class='oxd-form-actions']/button[@type='button']";
    @FindBy(xpath ="//div[@class='oxd-table-body']/div/div/div[2]/div")
    private  List<WebElement> idSkillList ;
    private final String idEditSkill ="//div[@class='oxd-table-cell-actions']/child::button[2]";

    private final String idSkillDelete ="//div[text()='ArchiCAD']//preceding::div[@class='oxd-table-cell oxd-padding-cell']//descendant::span";
    private final String idDeleteSelected ="//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']";
    private final String confirmDelete ="//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']";
    public SkillsPage(WebDriver driver) {

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void saveNewSkill(String name,String desc) {
        click(By.xpath(idSkillsAddBtn));
        setText(By.xpath(idESkillName), name);
        if(isElementVisible(By.xpath(idSkillExists))){
                    click(By.xpath(idSkillCancelBtn));
        }
        else{
            setText(By.xpath(idSkillDesc), desc);
            click(By.xpath(idSkillSaveBtn));
        }

    }


    public void getSkillNames(){
        sleep(5000);
        List<String>  skills =idSkillList.stream().map(s->s.getText()).collect(Collectors.toList());
        for (String skillName : skills ){
            System.out.println(skillName);
        }

    }

    public void editSkill(String newName, String newDesc){
        String editSkill ="AutoCAD";
        sleep(3000);
        List<String>  skills =idSkillList.stream().map(s->s.getText()).collect(Collectors.toList());
        if(skills.stream().anyMatch(s->s.equalsIgnoreCase(editSkill))){
            click(By.xpath(idEditSkill));
            sleep(3000);
            while (!skillName.getAttribute("value").equals("")) {
                skillName.sendKeys(Keys.BACK_SPACE);
            }
            skillName.sendKeys(newName);
            while (!skillDesc.getAttribute("value").equals("")) {
                skillDesc.sendKeys(Keys.BACK_SPACE);
            }
            skillDesc.sendKeys(newDesc);
            click(By.xpath(idSkillSaveBtn));
            }

        }



    public void deleteSkill(){
        String editSkill ="ArchiCAD";
        sleep(3000);
        List<String>  skills =idSkillList.stream().map(s->s.getText()).collect(Collectors.toList());
        if(skills.stream().anyMatch(s->s.equalsIgnoreCase(editSkill))){
            click(By.xpath(idSkillDelete));
            click(By.xpath(idDeleteSelected));
            click(By.xpath(confirmDelete));
        }


    }
}
