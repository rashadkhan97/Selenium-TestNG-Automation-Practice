package testrunner;

import Pages.LoginPage;
import Pages.ProfilePage;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import javax.swing.text.Utilities;
import java.io.IOException;
import java.time.Duration;

public class ProfileTestRunner extends Setup {


// since we separately test - ProfileTestRunner so Before profile update we have to login (its must)
    @BeforeTest
    public void doLogin() throws ParseException, IOException, InterruptedException {
        // Comment out the manual login part, Reason is in below
//            LoginPage loginPage = new LoginPage(driver);
//            loginPage.doLogin("admin@test.com","admin123");

        // Now without manually login, using token for going to profile page
        Utils.setAuthToken(driver);
    }

    // pre-requisite of updateProfile method
    @Test(description = "Admin Updates user information")
    public void updateProfilePage() throws InterruptedException {

    // Trying to set the token that's why this link is given, if we don't need to use set token just comment this line
        driver.get("https://dailyfinance.roadtocareer.net/admin");

        ProfilePage profilePage = new ProfilePage(driver);

//      Once Login to the page driver will wait till Total Users is not visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("total-count")));

        profilePage.btnView.get(1).click(); // click on edit
        // update user information provide
        profilePage.updateProfile("Md Rashadul", "Islam");
        profilePage.btnView.get(2).click();
        Utils.handleAlerts(driver);
    }
}
