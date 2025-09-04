package testrunner;

import org.testng.annotations.Test;
import Pages.LoginPage;
import setup.LoginDataset;
import setup.Setup;

public class LoginCSVTestRunner extends Setup {
    @Test(dataProvider = "LoginCSVData", dataProviderClass = LoginDataset.class)
    public void doLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email, password);
        loginPage.doLogout();
    }
}
