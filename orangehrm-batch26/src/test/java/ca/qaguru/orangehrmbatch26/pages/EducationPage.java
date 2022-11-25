package ca.qaguru.orangehrmbatch26.pages;


import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EducationPage extends PageBase {
    private final String idEduAddBtn = "btnAdd";
    private final String idEduTxtLevel="education_name";
    private final String idEduSaveBtn="btnSave";
    private final String getIdEduCancelBtn= "btnCancel";

    public EducationPage(WebDriver driver) {
        super(driver);
    }

    public void saveNewEducation(String level1) {
        click(By.id(idEduAddBtn));
        setText(By.id(idEduTxtLevel), level1);
        click(By.id(idEduSaveBtn));
    }
}
