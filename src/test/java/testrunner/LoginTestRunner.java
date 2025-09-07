package testrunner;

import Pages.LoginPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {

    @Test(priority = 1, description = "Admin login with wrong credentials")
    public void doLoginWithWrongCreds(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(System.getProperty("email"),System.getProperty("password")); //system.getProperty of email and password will be given through CMD/CLI for secure login
        String validationErrorActual = driver.findElement(By.tagName("p")).getText();
        String validationErrorExpected = "Invalid email or password";

        Assert.assertTrue(validationErrorActual.contains(validationErrorExpected));

        driver.navigate().refresh(); // once one test done page will refresh for next test
    }

    // Login with a last newly register user email and password. Which is in user.json file and this process done in Registration
    @Test(priority = 2, description = "User can login with valid credentials")
    public void doLoginByUser() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        // since user email and pass will be from user.json
        String filePath = "./src/test/resources/users.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filePath));
        JSONObject userObject = (JSONObject) jsonArray.get(jsonArray.size()-1);  // we will login with last user's info and since it's a jsonObject so storing into it
        // we will login with password and email
        String email = userObject.get("email").toString(); // we will get as array and convert to string
        String password = userObject.get("password").toString();

        loginPage.doLogin(email, password);
        loginPage.doLogout(); // once this done it will log out from the page. And this method comes from loginPage

    }

    @Test(priority = 3 , description = "Admin login with valid credentials")
    public void doLogin() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";

        Assert.assertTrue(headerActual.contains(headerExpected));
// when total users shown / displayed we can say login successful
        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed());
// for getting the token we comment the logout part and use getToken
        Utils.getAuthToken(driver);

    }



//    public void viewUserProfile(){
//        //List<WebElement> btnView= driver.findElements(By.cssSelector("[type=button]"));
//
//    }

}
