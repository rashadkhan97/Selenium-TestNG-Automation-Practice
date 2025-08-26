package setup;

import Pages.LoginPage;
import Pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void Setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //window maximize
        //  wait for a certain amount of time while trying to find an element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }

   // @AfterTest
    public void quitBrowser(){

    //Task - Logout from profile Menu
        //1. Click on profile Menu
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.btnView.get(0).click();

        // 2. Click on Logout page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.menuItem.get(1).click();
        driver.quit();
    }

}
