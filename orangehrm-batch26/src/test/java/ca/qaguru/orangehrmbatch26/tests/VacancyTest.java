package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.*;
import org.testng.annotations.Test;

public class VacancyTest extends TestBase {
    @Test(priority = 1)
    public void searchVacancy() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.VACANCIES);
        VacancyPage vacancy =new VacancyPage(driver);
        vacancy.searchVacancy("QA Lead","Senior QA Lead","Odis Adalwin","Active");

    }
}
