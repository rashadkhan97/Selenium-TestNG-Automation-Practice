package testrunner;

import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GmailService;
import setup.Setup;
import setup.UserModel;
import utils.Utils;
import Pages.RegistrationPage;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    @Test(priority = 1, description = "User can register successfully")
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailService gmailService = new GmailService();

        //batch 13 code

        /*
        //now it's time to pass the registration fields value
        //using java faker for random values
        Faker faker = new Faker();
        // we can use anything. It's for click on register to go to register page
        driver.findElement(By.partialLinkText("Register")).click();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email="mdroshungpt"+Utils.generateRandomId(100,999)+"@gmail.com";
        //we can use dynamically password but for now just using a fixed password
        String password = "1234"; // If we need dynamic password generate then -> String password = faker.internet().password(8, 12);
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

        Utils.saveData(jsonObject);
*/


        // batch 16 code
        Utils.scroll(driver,500);
        registrationPage.linkRegister.click();
        Faker faker=new Faker();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email="mdroshungpt+"+Utils.generateRandomId(100,999)+"@gmail.com";
        String password="1234";
        String phoneNumber="019"+Utils.generateRandomId(10000000, 99999999);
        String address=faker.country().capital();

        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(address);
        registrationPage.doRegister(userModel);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstName",firstname);
        jsonObject.put("lastName",lastname);
        jsonObject.put("email",email);
        jsonObject.put("password",password);

        try{
            Utils.saveData(jsonObject);
            System.out.println("Data saved successfully!");
            Thread.sleep(5000);
            String myEmail = gmailService.readLatestEmail();
            System.out.println(myEmail);

            Assert.assertTrue(myEmail.contains("Welcome to our platform"));
        }
        catch (Exception ex){
            System.out.println("Not saved "+ex);
        }

    }
}

