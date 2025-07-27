import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner extends Setup{

    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";

        Assert.assertTrue(headerActual.contains(headerExpected));

        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed());
    }
}
