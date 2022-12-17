package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import ca.qaguru.orangehrmbatch26.pages.UserManagementPage;
import org.testng.annotations.Test;

public class UserManagementDeleteUserTest extends TestBase {
    @Test
    public void deleteUserTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.USERS);
        UserManagementPage userManagement = new UserManagementPage(driver);
        userManagement.deleteUser("Admin","Peter123");
    }
}
