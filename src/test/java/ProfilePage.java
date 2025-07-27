import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {
    // View Button
    @FindBy(css = "[type = button]")
    List<WebElement> btnView;

    // #### For Editing User Details ####
    // First Name
    @FindBy(name = "firstName")
    WebElement txtFirstName;

    // Last Name
    @FindBy(name = "lastName")
    WebElement txtLastName;

    // Email Address
    @FindBy(name = "email")
    WebElement txtEmail;



// Initializes @FindBy elements with actual web page elements using the driver
    public ProfilePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

// This method is just used to update the details but its pre-requisite file -- ProfileTestRunner.java
    public void updateProfile(String firstName, String lastName, String email){
        // Update details firstName, lastName and email
        btnView.get(1).click(); // click on edit button inside user profile
        txtFirstName.sendKeys(firstName); // first name update
        txtLastName.sendKeys(lastName); // last name update
        txtEmail.sendKeys(email); // email update
        btnView.get(2).click(); // click on update button inside user profile

    }
}
