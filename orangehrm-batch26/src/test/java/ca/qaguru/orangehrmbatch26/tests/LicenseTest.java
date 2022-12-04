package ca.qaguru.orangehrmbatch26.tests;
import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LicensePage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import org.testng.annotations.Test;


public class LicenseTest extends TestBase {
    @Test
    public void newLicenseAddition() throws InterruptedException {
        String License="Anjana License New";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage=new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.LICENSES);
        LicensePage licensePage=new LicensePage(driver);
        licensePage.licenseAdd(License);
        Thread.sleep(3000);
        licensePage.licenseVerify(License);


    }

}




