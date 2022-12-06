package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.*;
import org.testng.annotations.Test;

public class MembershipTest extends TestBase {

    @Test
    public void MembershipList() throws InterruptedException {
        String newRecord = "Ontario Library Membership";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.MEMBERSHIPS);
      //  EducationPage educationPage = new EducationPage(driver);
        MembershipsPage membershipsPage = new MembershipsPage(driver);
        membershipsPage.selectMenu(MenuOptions.MEMBERSHIPS);
        Thread.sleep(3000);
        membershipsPage.clicklogin();

    }
}
