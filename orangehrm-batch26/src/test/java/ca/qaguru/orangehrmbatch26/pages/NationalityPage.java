package ca.qaguru.orangehrmbatch26.pages;

import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NationalityPage extends PageBase {
    public NationalityPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String idNatAddBtn = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']";
    private final String idNatTxtLevel = "//label[@class='oxd-label oxd-input-field-required']//following::input[1]";
    private final String idNatSaveBtn = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']";
    private final String SaveExistingNat = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']";
    private final String getIdNatCancelBtn = "//button[@class='oxd-button oxd-button--medium oxd-button--ghost']";
    private final String existMsg = "//div[@class='oxd-form-row'] /div";
    private final String deleteMsg = " //button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']";
    private final String noOfNationality = "//span[@class='oxd-text oxd-text--span']";
    private final String editTxtNationality = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";
    private final String lastPage = "//button[@class='oxd-pagination-page-item oxd-pagination-page-item--previous-next']//preceding::li[1]";
    private final String navigator = "//i[@class='oxd-icon bi-chevron-right']";
    private final String nationList = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String trashList = "//i[@class='oxd-icon bi-trash']";
    private final String editList = "//i[@class='oxd-icon bi-pencil-fill']";

    int total_nation,total_Page,tp;
    String totalPage;
    @FindBy(xpath = editTxtNationality)
    private WebElement editNation;

    @FindBy(xpath = lastPage)
    private WebElement lp;

    @FindBy(xpath = idNatAddBtn)
    private WebElement addButton;

    @FindBy(xpath = noOfNationality)
    private WebElement nationCount;

    @FindBy(xpath = SaveExistingNat)
    private WebElement ele;


    @FindBy(xpath = nationList)
    private List<WebElement> nations;

    @FindBy(xpath = trashList)
    private List<WebElement> trash;

    @FindBy(xpath = editList)
    private List<WebElement> edit;


    public void addNewNation(String nation) {
        click(By.xpath(idNatAddBtn));
        setText(By.xpath(idNatTxtLevel), nation);
        if (getText(By.xpath(existMsg)).contains("Already exists")) {
            click(By.xpath(getIdNatCancelBtn));
        } else {
            click(By.xpath(idNatSaveBtn));
        }
    }

    public int noOfPage() {
        totalPage = lp.getText();
        total_Page = Integer.parseInt(totalPage);
        return total_Page;
    }
    public void validateNewNation(String nation) throws InterruptedException {
        tp=noOfPage();
        for (int p = 1; p <tp; p++) {
            for (WebElement nat : nations)
            {
                String value=nat.getText();
                if(value.equals(nation))
                {
                    Thread.sleep(3000);
                    System.out.println("The new nation added is " +value);
                    break;
                }
            }
            click(By.xpath(navigator));
            Thread.sleep(8000);
        }
    }

    public void deleteNationality(String nation) throws InterruptedException {
        tp=noOfPage();
           for (int p = 1; p < tp; p++) {
            int total_row = trash.size();
            for (int i = 0; i < total_row; i++) {
                String value = nations.get(i).getText();
                if (value.equals(nation)) {
                    int r = i;
                    Thread.sleep(3000);
                    trash.get(r).click();
                    click(By.xpath(deleteMsg));
                    Thread.sleep(3000);
                    break;
                }
            }
            click(By.xpath(navigator));
            Thread.sleep(8000);
        }
    }
    public void validateDeleteNation(String nation) throws InterruptedException {
      driver.navigate().refresh();
       Thread.sleep(10000);
        int present = 0;
        for (int p = 1; p <tp; p++) {
            for (WebElement nat : nations)
            {
                String value=nat.getText();
                if(value.equals(nation))
                {
                    present= present+1;
                    break;
                }
            }
            click(By.xpath(navigator));
            Thread.sleep(8000);
        }
        if (present == 0) {
            System.out.println("The nation "+nation+ " is deleted");
        }
    }
    public void editNationality(String nation,String newNation) throws InterruptedException {
        tp=noOfPage();
        Actions a = new Actions(driver);
        for (int p = 1; p < total_Page; p++) {
            int total_row = edit.size();
            for (int i = 0; i < total_row; i++) {
                String value = nations.get(i).getText();
                if (value.equals(nation)) {
                    int r = i;
                    Thread.sleep(3000);
                    edit.get(r).click();
                    Thread.sleep(3000);
                    a.moveToElement(editNation);
                    Thread.sleep(3000);
                    a.doubleClick().build().perform();
                    Thread.sleep(3000);
                    a.sendKeys(Keys.BACK_SPACE, newNation).build().perform();
                    if (getText(By.xpath(existMsg)).contains("Already exists")) {
                        click(By.xpath(getIdNatCancelBtn));
                    } else {
                        click(By.xpath(idNatSaveBtn));
                    }
                    break;
                }

            }
            click(By.xpath(navigator));
            Thread.sleep(8000);
        }
    }
    public void validateEditNation(String nation,String newNation) throws InterruptedException {
        for (int p = 1; p <tp; p++) {
            for (WebElement nat : nations)
            {
                String value=nat.getText();
                if(value.equals(newNation))
                {
                    Thread.sleep(3000);
                    System.out.println("The nation " +nation);
                    System.out.print("is replaced by "+value);
                    break;
                }
            }
            click(By.xpath(navigator));
            Thread.sleep(8000);
        }
    }

    public void noOfNationality() {
        String text = nationCount.getText();
        total_nation = Integer.valueOf(text.substring(text.indexOf("(") + 1, text.indexOf(")")));
    }

    public void validateRecordCountOnAdd(String nation) throws InterruptedException {
        String addNation=nation;
        noOfNationality();
        int old_no = total_nation;
        addNewNation(addNation);
        Thread.sleep(8000);
        noOfNationality();
        Thread.sleep(2000);
        int new_no = total_nation;
        if (new_no == old_no + 1) {
            System.out.println("Total number of nationality is increased by 1 on adding a Nation");
        }
    }
    public void validateRecordCountOnDelete(String nation) throws InterruptedException {
        String deleteNation=nation;
        noOfNationality();
        int old_no = total_nation;
        deleteNationality(deleteNation);
        Thread.sleep(3000);
        noOfNationality();
        Thread.sleep(2000);
        int new_no = total_nation;
        if (new_no == old_no - 1) {
            System.out.println("Total number of nationality is decreased by 1 on deleting a Nation");
        }
    }

    public void saveExistingNationality(String nation) throws InterruptedException {
        click(By.xpath(idNatAddBtn));
        setText(By.xpath(idNatTxtLevel), nation);
        click(By.xpath(idNatSaveBtn));
        Thread.sleep(8000);
        String msg = ele.getText();
        if (msg.equals("Already exists")) {
            System.out.println("The Nation is already added");
        }
    }
    public void validateCancelButton() throws InterruptedException {
        click(By.xpath(getIdNatCancelBtn));
        Thread.sleep(8000);
        boolean t = addButton.isDisplayed();
        if (t == true) {
            System.out.println("The Cancel Button is working fine");
        }
    }
}



