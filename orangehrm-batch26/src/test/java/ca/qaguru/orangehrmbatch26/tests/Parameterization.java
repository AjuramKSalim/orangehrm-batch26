package ca.qaguru.orangehrmbatch26.tests;

import org.testng.annotations.Test;

public class Parameterization {
    @Test(dataProvider = "LoginInfo", dataProviderClass = ParameterizationData.class)
    public void sampleTest(String username, String password){
        System.out.println("Username : "+ username + " Password: "+ password);
    }
}
