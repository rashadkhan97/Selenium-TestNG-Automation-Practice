package testrunner;

import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import setup.UserModel;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    @Test(priority = 1, description = "User can register successfully")
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        //now it's time to pass the registration fields value
        //using java faker for random values
        Faker faker = new Faker();
        driver.findElement(By.partialLinkText("Register")).click();  // we can use anything. It's for click on register to go to register page
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=firstName.toLowerCase()+"@gmail.com";
        String password = faker.internet().password(8, 12);
        // both password and phone number is getting from Utils....
        //password is declared as a String, that's why converted int to string value for password filed
       // String password= String.valueOf(Utils.generateRandomId(10000, 99999));
        //randomly generated 8 digits and fixed 3 digits = 11 digits for phone number
        String phoneNumber="019"+Utils.generateRandomId(10000000, 99999999);
        registrationPage.doRegister(firstName,lastName,email,password,phoneNumber);
        Thread.sleep(3000);

        // successful message appear -- which is actually called toast message
        String successMessage = driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("registered successfully"));


        // Create a new JSON object to store the new user's details
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",firstName);  // which info need we will just put. If any not need just remove
        jsonObject.put("lastName", lastName);
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("phoneNUmber", phoneNumber);

        // saving data
        Utils.saveData(jsonObject);
    }
}

