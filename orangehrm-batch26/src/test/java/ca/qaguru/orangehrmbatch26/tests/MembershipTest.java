package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MembershipsPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import org.testng.annotations.Test;

public class MembershipTest extends TestBase {
    String membership = "Ontario Library Membership";
    String newMember ="AHS health Organization";
    @Test
    public void MembershipList() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.MEMBERSHIPS);
        MembershipsPage membershipsPage = new MembershipsPage(driver);
        membershipsPage.MembershipAddBtn(membership);
        membershipsPage.memberVerify(membership);




    }
}