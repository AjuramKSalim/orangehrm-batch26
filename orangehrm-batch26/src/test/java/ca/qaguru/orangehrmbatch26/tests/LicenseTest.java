package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LicensePage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LicenseTest extends TestBase {

    @Test
    public void addNewLicense() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.LICENSES);
        LicensePage licensePage = new LicensePage(driver);
       licensePage.licenseNewAdd("Johny");
       licensePage.newLicenseVerify();



       }

    }


