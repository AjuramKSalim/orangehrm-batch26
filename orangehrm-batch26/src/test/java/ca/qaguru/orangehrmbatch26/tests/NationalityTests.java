package ca.qaguru.orangehrmbatch26.tests;

import ca.qaguru.orangehrmbatch26.library.TestBase;
import ca.qaguru.orangehrmbatch26.pages.*;
import org.testng.annotations.Test;



public class NationalityTests extends TestBase {

    @Test(priority = 1)
    public void addNewNationality() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.addNewNation("New2");
        Thread.sleep(8000);
        nationalityPage.validateNewNation("New2");
    }


    @Test(priority = 2)
    public void deleteNation() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.deleteNationality("New");
        Thread.sleep(3000);
        nationalityPage.validateDeleteNation("New");
    }

    @Test(priority = 3)
    public void editNation() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.editNationality("Liberian","NewNation");
        nationalityPage.validateEditNation("Liberian","NewNation");
        Thread.sleep(3000);

    }

    @Test(priority = 4)
    public void validateRecordCountOnAdd() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.validateRecordCountOnAdd("NewNation2");
    }

    @Test(priority = 5)
    public void validateRecordCountOnDelete() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.validateRecordCountOnDelete("NewNation2");
    }

    @Test(priority = 6)
    public void validateExistingNationNCancelButton() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        Thread.sleep(3000);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        Thread.sleep(3000);
        NationalityPage nationalityPage = new NationalityPage(driver);
        nationalityPage.saveExistingNationality("Afghan");
        nationalityPage.validateCancelButton();

    }
}







