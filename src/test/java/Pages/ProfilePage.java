package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {
    // View Button
    @FindBy(css = "[type = button]")
    public List<WebElement> btnView;

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

// This method is just used to update the details but its pre-requisite file -- testrunner.ProfileTestRunner.java
    public void updateProfile(String firstName, String lastName){
        // Update details firstName, lastName and email
        btnView.get(1).click(); // click on edit button inside user profile

        // first clear previous firstName then provide new firstName
        txtFirstName.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        txtFirstName.sendKeys(firstName); // first name update

        // first clear previous LastName then provide new Name
        txtLastName.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        txtLastName.sendKeys(lastName); // last name update
//
//        // first clear previous email then provide new Email
//        txtEmail.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
//        txtEmail.sendKeys(email); // email update
        btnView.get(2).click(); // click on update button inside user profile

    }
}