import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProfileTestRunner extends Setup {


// since we separately test ProfileTestRunner so Before profile update we have to login (its must)
    @BeforeTest
    public void doLogin(){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin("admin@test.com","admin123");
    }

    // pre-requisite of updateProfile method
    @Test
    public void updateProfilePage(){
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.btnView.get(1).click(); // click on edit
        // update user information provide
        profilePage.updateProfile("Md Rashadul", "Islam", "rsrashad@gmail.com");
        profilePage.btnView.get(2).click();

    }
}
