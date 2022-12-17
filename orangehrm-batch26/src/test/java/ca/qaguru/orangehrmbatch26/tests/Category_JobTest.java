package ca.qaguru.orangehrmbatch26.tests;

        import ca.qaguru.orangehrmbatch26.library.TestBase;
        import ca.qaguru.orangehrmbatch26.pages.*;
        import org.testng.annotations.Test;

public class Category_JobTest extends TestBase {
    String category_Job = "Technician";
    String category_JobNew = "Junior Technician";

    @Test(priority = 1)
    public void addCategory_Job() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.JOB_CATEGORIES);
        Category_Job category_jobPage = new Category_Job(driver);
        category_jobPage.Category_JobAdd(category_Job);
        category_jobPage.Category_JobAdd(category_Job);
    }

    @Test(priority = 2)
    public void editCategory_job() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.JOB_CATEGORIES);
        CategoryEdit category_jobPage = new CategoryEdit(driver);
        category_jobPage.category_JobEdit(category_Job, category_JobNew);
        category_jobPage.category_JobEditVerify(category_Job, category_JobNew);
    }

    @Test(priority = 3)
    public void deleteCategory_Job() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.JOB_CATEGORIES);
        CategoryDelete category_jobPage = new CategoryDelete(driver);
        category_jobPage.category_JobDelete(category_JobNew);
        category_jobPage.category_JobDeleteVerify(category_JobNew);
    }

}




