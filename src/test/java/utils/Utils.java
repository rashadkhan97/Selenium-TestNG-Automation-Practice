package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.UserModel;

import java.io.*;
import java.time.Duration;

public class Utils {
    public static void handleAlerts(WebDriver driver) throws InterruptedException {
        // wait for alert and accept it
        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitAlert.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

    }

    public static int generateRandomId(int min, int max){
        //generating random id with math.random
        double randomId = Math.random()*(max-min)+min;
        return (int) randomId; // double value parse to int
    }

    // used just for checking random id is generating or not (it doesn't matter to have or not)
    public static void main(String[] args) {
        System.out.println(generateRandomId(1000,9999));
    }

    // Save users Registration Data
    public static void saveData(JSONObject jsonObject) throws IOException, ParseException {
        // Define the file path where the JSON data will be saved
        String filePath = "./src/test/resources/users.json";

        // Create a JSON parser object to read existing data from the file
        JSONParser jsonParser = new JSONParser();

        // Parse the existing JSON data in the file (it contains an array of users)
        // Cast the parsed object into a JSONArray because our file stores multiple user objects
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));

        // Add the newly created user object into the existing JSON array
        jsonArray.add(jsonObject);

        // Write the updated JSON array back into the file (overwrite the old content)
        FileWriter writer = new FileWriter(filePath);
        writer.write(jsonArray.toJSONString());  // Convert JSONArray to String and write
        writer.flush();                          // Ensure all data is written
        writer.close();                          // Close the file writer to free resources

    }
}
