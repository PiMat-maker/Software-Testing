package BrowsersTest;

import AccountPage.AccountPage;
import AudiencePage.AudiencePage;
import LoginPage.LoginPage;
import MailchimpPresentsPage.MailchimpPresentsPage;
import MailchimpPresentsPage.VideoPage;
import MainPage.MainPage;
import MarketingPlatformPage.MarketingPlatformPage;
import AccountPage.ProfilePage;
import SeleniumPage.SeleniumPage;
import SeleniumPage.SeleniumPageTest;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeTest extends SeleniumPageTest {

    @BeforeEach
    void initPage() {
        WebDriverManager.chromedriver().setup();
        Config config = ConfigFactory.load("metadata.conf");
        String url = config.getString("url");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get(url);
        SeleniumPage.setDriver(driver);
        mainPage =  new MainPage(driver);
        accountPage = new AccountPage(driver);
        audiencePage = new AudiencePage(driver);
        loginPage = new LoginPage(driver);
        mailchimpPresentsPage = new MailchimpPresentsPage(driver);
        videoPage = new VideoPage(driver);
        marketingPlatformPage = new MarketingPlatformPage(driver);
        profilePage = new ProfilePage(driver);
    }
}
