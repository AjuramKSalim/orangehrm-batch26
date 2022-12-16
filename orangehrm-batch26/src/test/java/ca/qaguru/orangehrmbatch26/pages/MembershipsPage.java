package ca.qaguru.orangehrmbatch26.pages;


import ca.qaguru.orangehrmbatch26.library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MembershipsPage extends PageBase {

    WebDriver driver;

    public MembershipsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String MembershipAddBtn = ".oxd-button--secondary";
    private final String RecordExists = "//div[@class='oxd-form-row'] /div";
    private final String idRecordTextLevel = "//div[@class='oxd-form-row'] /div[1] /div[2] /input";
    private final String idMemSaveBtn = "//button[@type='submit']";
    private final String tblMembership = "//div[@class='orangehrm-container']";
    private final String getIdMemCancelBtn = "//div[@class='oxd-form-actions'] /button[1]";

    private final String getItemSelect = ".oxd-table-cell.oxd-padding-cell";

    private final String tblmember = ".oxd-table-body";
    private final String memberdeletepop = "//button[text()=' Yes, Delete ']";
    private final String memberdeletepopcontainer = ".orangehrm-dialog-popup";
    private final String members = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String memberdelete = ".oxd-icon.bi-trash";
    private final String editBtn = ".oxd-icon.bi-pencil-fill";
    private final String memberEditBtn = "//form[@class='oxd-form'] /div[@class='oxd-form-row'] /div /div[2]";

    @FindBy(css = ".oxd-table-card")
    private List<WebElement> memberships;

    @FindBy(xpath = memberEditBtn)
    private List<WebElement> memberedit  ;

    @FindBy(css = editBtn)
    private List<WebElement> EditBtn;

    @FindBy(css = memberdelete)
    private List<WebElement> memberdeletebutton;


    public boolean MembershipAddBtn(String membership) {

        click(By.cssSelector(MembershipAddBtn));
        setText(By.xpath(idRecordTextLevel), membership);
        sleep(3000);
        if (getText(By.xpath(RecordExists)).contains("Already exists")) {
            click(By.xpath(getIdMemCancelBtn));
        } else {
            click(By.xpath(idMemSaveBtn));
        }
        boolean wait = isElementVisible(By.cssSelector(tblMembership));
        return wait;
    }


    public void memberVerify(String membership) {

        int length = memberships.size();

        for (WebElement value : memberships) {
            String text = value.getText();
            System.out.println(text);
        }

    }

    @FindBy(xpath = members)
    private List<WebElement> memberlist;

    public boolean getMemberName() {

        List<String> Membername = memberlist.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println(" ");
        System.out.println("member list  in the table:\n");
        for (String Membertxt : Membername) {
            System.out.println(Membertxt);


        }
        return false;
    }

}