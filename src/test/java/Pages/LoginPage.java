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

// initially it's an empty/null driver. This has no value so what will we do if we look in pageFactory we will
// see that WebDriver is fully loaded. So to use this driver we just use this.driver = driver inside pageFactory,
// and we will be able to use it
    WebDriver driver;

    // Initializes @FindBy elements with actual web page elements using the driver
    public LoginPage(WebDriver driver){
        this.driver=driver; // By doing this, the loaded driver will be able to use by other of this page.
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    // Logout task
    public void doLogout(){

        //Task - Logout from profile Menu
        //1. Click on profile Menu
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.btnView.get(0).click();

        // 2. Click on Logout page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.menuItem.get(1).click();
    }


}
