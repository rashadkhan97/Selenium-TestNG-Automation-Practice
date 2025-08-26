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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        loginPage.doLogout(); // once this done it will log out from the page. And this method comes from loginPage
    }

    // Login with a last newly register user email and password. Which is in user.json file and this process done in Registration
    @Test(priority = 3, description = "User can login with valid credentials")
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

    }

//    public void viewUserProfile(){
//        //List<WebElement> btnView= driver.findElements(By.cssSelector("[type=button]"));
//
//    }

}
