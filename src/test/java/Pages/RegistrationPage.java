package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.UserModel;

import java.util.List;

public class RegistrationPage {
    @FindBy(partialLinkText = "Register")
    public WebElement linkRegister;

    @FindBy(id = "firstName")
    WebElement txtFirstName;

    @FindBy(id = "lastName")
    WebElement txtLastName;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(css = "[type=radio]")
    List<WebElement> radioBtn;

    @FindBy(css = "[type=checkbox]")
    WebElement chkTerms;

    @FindBy(id = "register")
    WebElement registerBtn;

    // Initializes @FindBy elements with actual web page elements using the driver
    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doRegister(UserModel userModel){
        txtFirstName.sendKeys(userModel.getFirstname());
        txtLastName.sendKeys(userModel.getLastname());
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        radioBtn.get(0).click(); // selecting male with get(0)
        chkTerms.click();
        registerBtn.click();
    }


}
