package testrunner;

import Pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;

public class LoginTestRunner extends Setup {

    @Test(priority = 1, description = "Admin login with wrong credentials")
    public void doLoginWithWrongCreds(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "134");
        String validationErrorActual = driver.findElement(By.tagName("p")).getText();
        String validationErrorExpected = "Invalid email or password";

        Assert.assertTrue(validationErrorActual.contains(validationErrorExpected));

        driver.navigate().refresh(); // once one test done page will refresh for next test
    }

    @Test(priority = 2 , description = "Admin login with valid credentials")
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";

        Assert.assertTrue(headerActual.contains(headerExpected));
// when total users shown / displayed we can say login successful
        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed());
    }

    public void viewUserProfile(){
        //List<WebElement> btnView= driver.findElements(By.cssSelector("[type=button]"));

    }
}
