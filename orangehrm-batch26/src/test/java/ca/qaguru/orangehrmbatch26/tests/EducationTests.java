package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.EducationPage;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import org.testng.annotations.Test;

import java.util.UUID;

public class EducationTests extends TestBase {

    @Test
    public void addNewEducation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EDUCATION);
        EducationPage educationPage = new EducationPage(driver);
        educationPage.saveNewEducation("Level1");
        String uuid = UUID.randomUUID().toString();
        educationPage.saveNewEducation("level1" + uuid);
    }
}

