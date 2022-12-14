package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.*;
import org.openqa.selenium.By;
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
        membershipsPage.memberEdit(membership,newMember);

        membershipsPage.memberEditedVerify(membership,newMember);
        membershipsPage.memberDelete(membership);
        membershipsPage.memberDeleteVerify(membership);



    }
}
/*
-------------------------------

package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.HeaderPage;
import ca.qaguru.orangehrmbatch26.pages.LicensesPage;
import ca.qaguru.orangehrmbatch26.pages.LoginPage;
import ca.qaguru.orangehrmbatch26.pages.MenuOptions;
import org.testng.annotations.Test;


public class LicensesTest extends TestBase {
    String license = "CompTIA A+ Certification";
    String licenseNew = "AWS Cloud Practitioner";

    @Test(priority = 1)
    public void addLicense() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.LICENSES);
        LicensesPage licensePage = new LicensesPage(driver);
        licensePage.licenseAdd(license);
        licensePage.licenseAddVerify(license);
    }

    @Test(priority = 2)
    public void editLicense() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.LICENSES);
        LicensesPage licensePage = new LicensesPage(driver);
        licensePage.licenseEdit(license, licenseNew);
        licensePage.licenseEditVerify(license, licenseNew);
    }

   @Test(priority = 3)
    public void deleteLicense() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.LICENSES);
        LicensesPage licensePage = new LicensesPage(driver);
        licensePage.licenseDelete(licenseNew);
        licensePage.licenseDeleteVerify(licenseNew);
    }
}*/