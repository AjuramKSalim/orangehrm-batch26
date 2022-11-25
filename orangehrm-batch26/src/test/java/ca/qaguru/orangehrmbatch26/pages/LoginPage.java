package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends PageBase {

    private String idUsername = "username";
    private String idPassword = "password";
    private String idLogin = "//button[@type='submit']";
    private String idErrMsg = "spanMessage";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password, boolean validCredentials, String expErrMsg){
        setText(By.name(idUsername), username);
        setText(By.name(idPassword),password);
        click(By.xpath(idLogin));

        HeaderPage headerPage = new HeaderPage(driver);
        if(validCredentials){
            Assert.assertTrue(headerPage.isMenuVisible());
        }else {
            Assert.assertFalse(headerPage.isMenuVisible());
            Assert.assertEquals(getText(By.id(idErrMsg)),expErrMsg, "Incorrect Error Message");
        }
    }

}
