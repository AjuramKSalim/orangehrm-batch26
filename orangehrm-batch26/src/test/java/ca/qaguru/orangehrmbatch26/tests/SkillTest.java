package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import ca.qaguru.orangehrmbatch26.pages.SkillsPage;
import org.testng.annotations.Test;


public class SkillTest extends TestBase {

    @Test(priority = 1)
    public void addNewSkill() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.SKILLS);
        SkillsPage skill = new SkillsPage(driver);
        skill.saveNewSkill("AutoCAD","Drafting software");
        skill.getSkillNames();

    }


    @Test(priority = 2)
    public void editASkill(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.SKILLS);
        SkillsPage skill = new SkillsPage(driver);
        skill.editSkill("ArchiCAD","New Software");
        skill.getSkillNames();

    }

    @Test(priority = 3)
    public void deleteAddedSkill() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.SKILLS);
        SkillsPage skill = new SkillsPage(driver);
        skill.deleteSkill();
        skill.getSkillNames();
    }



}
