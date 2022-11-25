package ca.qaguru.orangehrmbatch26.library;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class PageBase {
    protected WebDriver driver;
    private final long WAIT_TIME = 5L;
    private static final int DEF_MAX_TRIALS = 10;
    private static final int REATTEMPT_DELAY = 500;
    protected static final int MENU_SELECTION_DELAY = 1000;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
    protected void click(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by))
                    .click();
            log.debug("click({}) done sucessfully",by);

        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("click({}) failed - StaleElementReferenceException. Attempting again...",by);
            click(by);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("click({}) failed - ElementNotInteractableException. Attempting again...",by);
            click(by);
        }
    }
    protected void mouseHover(By by) {
        try {
            WebElement we = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
            Actions action = new Actions(driver);
            action.moveToElement(we).build().perform();
            log.debug("mouseHover({}) done sucessfully",by);

        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("mouseHover({}) failed - StaleElementReferenceException. Attempting again...",by);
            mouseHover(by);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("mouseHover({}) failed - ElementNotInteractableException. Attempting again...",by);
            mouseHover(by);
        }
    }
    protected void mouseMove(int offsetX, int offsetY) {
        try {
            Actions action = new Actions(driver);
            action.moveByOffset(offsetX,offsetY).build().perform();
            log.debug("mouseMove({},{}) done sucessfully",offsetX,offsetY);

        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("mouseMove({},{}) failed - StaleElementReferenceException. Attempting again...",offsetX,offsetY);
            mouseMove(offsetX,offsetY);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("mouseMove({},{}) failed - StaleElementReferenceException. Attempting again...",offsetX,offsetY);
            mouseMove(offsetX,offsetY);
        }
    }

    protected void setText(By by, String text, int ... retries) {
        if((text != null && text.length() != 0)) {
            if(retries.length==0){
                retries = new int[1];
                retries[0] = DEF_MAX_TRIALS;
            }

            if(retries[0]<=0) {
                log.debug("setText({},{}) failed. Terminating after maximum number of attempts",by, text);
                Assert.fail("setText("+by+","+text+") failed. Terminating after maximum number of attempts" );
                return;
            }

            try {
                WebElement we = new WebDriverWait(driver, WAIT_TIME)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(by));
                we.clear();
                we.sendKeys(text);
                String value = driver.findElement(by).getAttribute("value");
                if(!value.equals(text)){
                    log.debug("setText({},{}) failed. Attempting again...",by, text);
                    setText(by,text,retries[0]-1);
                }else{
                    log.debug("setText({},{}) done sucessfully",by, text);
                }

            } catch (StaleElementReferenceException exception) {
                log.debug("setText({},{}) failed-StaleElementReferenceException. Attempting again...",by, text);
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            } catch (ElementNotInteractableException exception) {
                log.debug("setText({},{}) failed-ElementNotInteractableException. Attempting again...",by, text);
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            }catch (WebDriverException exception){
                log.debug("setText({},{})failed : WebDriverException caught",by,text);
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            }
        }
    }

    protected void fileUpload(By by, String text, int ... retries) {
        if((text != null && text.length() != 0)) {
            if(retries.length==0){
                retries = new int[1];
                retries[0] = DEF_MAX_TRIALS;
            }

            if(retries[0]<=0) {
                log.debug("fileUpload({},{}) failed. Terminating...",by, text);
                Assert.fail("fileUpload("+by+","+text+") failed. Terminating..." );
                return;
            }

            try {
                WebElement we = new WebDriverWait(driver, WAIT_TIME)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(by));
                we.sendKeys(text);
                String value = driver.findElement(by).getAttribute("value");

            } catch (StaleElementReferenceException exception) {
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            } catch (ElementNotInteractableException exception) {
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            } catch (WebDriverException exception){
                log.debug("fileUpload({},{}) : WebDriverException caught",by.toString(),text);
                sleep(REATTEMPT_DELAY);
                setText(by,text,retries[0]-1);
            }
        }
    }

    protected void select(By by, String visibleText) {
        try {
            new Select(new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by)))
                    .selectByVisibleText(visibleText);
            log.debug("select({},{}) done sucessfully",by, visibleText);
        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("select({},{}) failed with StaleElementReferenceException. Attempting again...",by, visibleText);
            select(by,visibleText);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            log.debug("select({},{}) failed with ElementNotInteractableException. Attempting again...",by, visibleText);
            select(by,visibleText);
        }catch (NoSuchElementException exception){
            log.error("select({},{}) failed. Item to be selected not found in the list box ",by, visibleText);
            log.error("Available items in the Listbox- {}",getListBoxItems(by));
            Assert.fail("select() failed with NoSuchElementException.");

        }
    }
    protected List<String> getListBoxItems(By by) {
        try {
            List<String> items =  new Select(new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by)))
                    .getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
            log.debug("getListBoxItems({}) done sucessfully",by);
            return items;
        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            getListBoxItems(by);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            getListBoxItems(by);
        }
        return null;
    }


    protected String getText(By by) {
        return new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by))
                .getText();
    }
    protected boolean isElementPresent(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .presenceOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    protected boolean isElementVisible(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    protected boolean isElementClickable(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    protected void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Ignore
    protected void clickJSE(By by){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by));
        js.executeScript("arguments[0].click()", webElement);
    }
    protected void scrollInToView(By by){
        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            scrollInToView(by);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            scrollInToView(by);
        }
    }

    //Ignore this
    protected void clickSelectButton(By by, int ... retries){
        if(retries.length==0){
            retries = new int[1];
            retries[0] = DEF_MAX_TRIALS;
        }

        if(retries[0]<=0) return;

        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            click(by);
            String isSelected = webElement.getAttribute("class");
            if(!isSelected.contains("selected-true")){
                sleep(REATTEMPT_DELAY);
                clickSelectButton(by, retries[0]-1);
            }

        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            clickSelectButton(by,retries[0]-1);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            clickSelectButton(by,retries[0]-1);
        }
    }

    //Ignore this
    protected void selectOptionButton(String label,String option, int ... retries){
        String selectButton = "//*[text()='XXX']//following::label[text()='YYY'][1]//parent::span"
                .replace("XXX",label)
                .replace("YYY", option);
        By by = By.xpath(selectButton);
        if(retries.length==0){
            retries = new int[1];
            retries[0] = DEF_MAX_TRIALS;
        }

        if(retries[0]<=0) {
            log.error("selectOptionButton({},{}) failed after many trials",label,option);
            return;
        }

        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            click(by);
            String isSelected = webElement.getAttribute("class");
            if(!isSelected.contains("selected-true")){
                sleep(REATTEMPT_DELAY);
                clickSelectButton(by, retries[0]-1);
            }

        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            clickSelectButton(by,retries[0]-1);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            clickSelectButton(by,retries[0]-1);
        }catch (WebDriverException exception){
            log.debug("selectOptionButton({},{}) : WebDriverException caught",label,option);
            sleep(REATTEMPT_DELAY);
            clickSelectButton(by,retries[0]-1);
        }
    }
    protected Rectangle getRect(By by){
        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(by));
            return webElement.getRect();
        } catch (StaleElementReferenceException exception) {
            sleep(REATTEMPT_DELAY);
            scrollInToView(by);
        }catch (ElementNotInteractableException exception) {
            sleep(REATTEMPT_DELAY);
            scrollInToView(by);
        }
        return null;
    }
}
