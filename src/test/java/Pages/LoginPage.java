package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    // Login Page Email Field
    @FindBy(id = "email")
    WebElement txtEmail;

    // Login Page Password Field
    @FindBy(id = "password")
    WebElement txtPassword;

    // Login Page Submit Button
    @FindBy(css = "[type = submit]")
    WebElement btnLogin;

    //LogoutButton inside Profile Menu
    @FindBy(css = "[role= menuitem]")
    public List<WebElement> menuItem;

    // Initializes @FindBy elements with actual web page elements using the driver
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }


}
