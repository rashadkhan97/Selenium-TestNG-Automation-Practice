package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailService {

    Properties prop;
    public GmailService() throws IOException, IOException { //created a constructor for token load / write
        // Need to load / write token first to access
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    public String getGmailList(){
        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json").header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().get("gmail/v1/users/me/messages");
        //System.out.println(res.asString());

        //Extract latest mail id value using json object -- using jsonPath
        JsonPath jsonObj = res.jsonPath();
        String listId = jsonObj.get("messages[0].id");
        return listId;
    }

    public String readLatestEmail() throws IOException {
        // to get id so that we can read the latest mail
        GmailService gmailService = new GmailService(); //object create
        String messageId = gmailService.getGmailList();

        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json").header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().get("gmail/v1/users/me/messages/"+messageId);
        //System.out.println(res.asString());

        //Extract latest Email data using json object -- using jsonPath
        JsonPath jsonObj = res.jsonPath();
        String myEmail = jsonObj.get("snippet");
        return myEmail;
    }

    public static void main(String[] args) throws IOException {
        GmailService gmailService = new GmailService(); //object create
        String myEmail = gmailService.readLatestEmail();
        System.out.println(myEmail);
    }
}
