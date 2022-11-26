package ca.qaguru.orangehrmbatch26.tests;

import org.testng.annotations.DataProvider;

public class ParameterizationData {
    @DataProvider(name = "LoginInfo")
    public Object[][] getUserInfo(){
        return new Object[][]{
                {"admin","admin123"},
                {"joe", "joe123"},
                {"Hary", "Password"}
        };
    }
}
