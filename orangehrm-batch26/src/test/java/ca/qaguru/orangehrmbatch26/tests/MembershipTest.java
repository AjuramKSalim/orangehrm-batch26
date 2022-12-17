package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MembershipTest extends TestBase {
    String membership = "Ontario Library Membership";
    @Test
    public void MembershipList() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.MEMBERSHIPS);
        MembershipsPage membershipsPage = new MembershipsPage(driver);
        membershipsPage.MembershipAddBtn(membership);
        membershipsPage.memberVerify(membership);
        membershipsPage.memberSelect(2);



    }
}
