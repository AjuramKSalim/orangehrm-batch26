package ca.qaguru.orangehrmbatch26.pages;


import ca.qaguru.orangehrmbatch26.library.PageBase;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HeaderPage extends PageBase {
    private String lblWelcome = "//*[@id='welcome']";
    private String mnuItem ="//*[@class='oxd-main-menu']//descendant::*[text()='XXX']";
    private String mnuItem1 ="//*[@class='oxd-topbar-body-nav']//descendant::*[contains(text(),'XXX')]";

    private String idMenu  ="oxd-main-menu";
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void assertWelcomeMessage(){
        Assert.assertTrue(isElementVisible(By.xpath(lblWelcome)),"Login not successful");
    }
    public void selectMenu(String menu){//Admin|Job|Job Title
        List<String> menuItems = Arrays.asList(menu.split("\\|"));
        Dimension dimension = driver.manage().window().getSize();
        log.debug("Window size height :{}, width :{}", dimension.height,dimension.width);

        int menuLevel=0;
        for (String item:menuItems){
            
            menuLevel++;
            if(menuLevel==1) {
                By byMenu = By.xpath(mnuItem.replace("XXX", item));
                Rectangle rectangle = getRect(byMenu);
                log.debug("Menu item coordinates- X1:{}, Y1:{}, X2 :{}, Y2 :{}", rectangle.getX(), rectangle.getY(),
                        rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight());

                mouseHover(byMenu);

                sleep(MENU_SELECTION_DELAY);
                click(byMenu);
            }
            else {
                By byMenu1 = By.xpath(mnuItem1.replace("XXX", item));
                Rectangle rectangle1 = getRect(byMenu1);
                log.debug("Menu item coordinates- X1:{}, Y1:{}, X2 :{}, Y2 :{}", rectangle1.getX(), rectangle1.getY(),
                        rectangle1.getX() + rectangle1.getWidth(), rectangle1.getY() + rectangle1.getHeight());

                mouseHover(byMenu1);

                sleep(MENU_SELECTION_DELAY);
                click(byMenu1);
            }

        }

    }
    public boolean isMenuVisible(){
        return isElementVisible(By.className(idMenu));
    }

}
