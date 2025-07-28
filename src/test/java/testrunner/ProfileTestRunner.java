package testrunner;

import Pages.LoginPage;
import Pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import javax.swing.text.Utilities;
import java.time.Duration;

public class ProfileTestRunner extends Setup {


// since we separately test - ProfileTestRunner so Before profile update we have to login (its must)
    @BeforeTest
    public void doLogin(){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin("admin@test.com","admin123");
    }

    // pre-requisite of updateProfile method
    @Test(description = "Admin Updates user information")
    public void updateProfilePage() throws InterruptedException {
        ProfilePage profilePage = new ProfilePage(driver);

//      Once Login to the page driver will wait till Total Users is not visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("total-count")));

        profilePage.btnView.get(1).click(); // click on edit
        // update user information provide
        profilePage.updateProfile("MdRashadul", "Islam", "rsrasddrashad@gmail.com");
        profilePage.btnView.get(2).click();
        Utils.handleAlerts(driver);
    }
}
