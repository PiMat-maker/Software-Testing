package SeleniumPage;

import AccountPage.AccountPage;
import AudiencePage.AudiencePage;
import LoginPage.LoginPage;
import MailchimpPresentsPage.MailchimpPresentsPage;
import MailchimpPresentsPage.VideoPage;
import MainPage.MainPage;
import MarketingPlatformPage.MarketingPlatformPage;
import AccountPage.ProfilePage;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class SeleniumPageTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected AccountPage accountPage;
    protected AudiencePage audiencePage;
    protected LoginPage loginPage;
    protected MailchimpPresentsPage mailchimpPresentsPage;
    protected VideoPage videoPage;
    protected MarketingPlatformPage marketingPlatformPage;
    protected ProfilePage profilePage;


    @AfterEach
    void closeAll() {
        driver.quit();
    }

    @Test
    public void changeLanguage(){
        mainPage.getLanguageSelector().click();
        mainPage.getLanguages().get(2).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/a[@href=\"https://login.mailchimp.com/\"]")));
        String loginButtonText = mainPage.getLogInButton().getText();
        Assertions.assertTrue(loginButtonText.contains("Se connecter"));
    }

    @Test
    public void chooseFreeMarketingPlan(){
        mainPage.getWinRepeatBusinessButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(audiencePage.getPickPlanButton()));
        audiencePage.getPickPlanButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(marketingPlatformPage.getSignUpFreeButton()));
        marketingPlatformPage.getSignUpFreeButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));
        String text = driver.findElement(By.xpath("//h1")).getText();
        Assertions.assertTrue(text.contains("Welcome to Mailchimp"));
    }

    @Test
    public void chooseAccountsAmount() throws InterruptedException {
        mainPage.getWinRepeatBusinessButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(audiencePage.getPickPlanButton()));
        audiencePage.getPickPlanButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id = \"all_plans_contacts_select\"]")));
        marketingPlatformPage.getContactsAmountSelector().click();
        marketingPlatformPage.getContactsAmounts().get(4).click();
        Thread.sleep(3000);
        int price = Integer.parseInt(driver.findElement(By.xpath("//div[3]/div/div/div/div/div/div[@data-behavior = \"formattedPrice__integer\"]"))
                .getText());
        Assertions.assertEquals(87, price);
    }

    @Test
    public void searchCategoryVideo() throws InterruptedException {
        mainPage.getFilmsButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(mailchimpPresentsPage.getMediaTypeButtons().get(1)));
        mailchimpPresentsPage.getMediaTypeButtons().get(1).click();
        Thread.sleep(3000);
        String title = mailchimpPresentsPage.getTitleText();
        Assertions.assertTrue(title.contains("Watch"));
    }

    @Test
    public void watchVideo(){
        mainPage.getFilmsButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(mailchimpPresentsPage.getMediaTypeButtons().get(1)));
        mailchimpPresentsPage.getMediaTypeButtons().get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(mailchimpPresentsPage.getVideos().get(0)));
        mailchimpPresentsPage.getVideos().get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(videoPage.getErrorButton()));
        String text = videoPage.getErrorButton().getText();
        Assertions.assertTrue(text.contains("Please update your privacy settings to enable video play."));
    }

    @Test
    public void Login(){
        Config config = ConfigFactory.load("metadata.conf");
        String login = config.getString("userData.user.login");
        String password = config.getString("userData.user.password");
        mainPage.getLogInButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));
        loginPage.setLoginText(login);
        loginPage.setPassword(password);
        loginPage.getLoginButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getAccountButton()));
        String text = accountPage.getAccountImage().getAttribute("alt");
        Assertions.assertTrue(text.contains("Signed in"));
    }

    @Test
    public void checkBillingHistory(){
        Config config = ConfigFactory.load("metadata.conf");
        String login = config.getString("userData.user.login");
        String password = config.getString("userData.user.password");
        mainPage.getLogInButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));
        loginPage.setLoginText(login);
        loginPage.setPassword(password);
        loginPage.getLoginButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getAccountButton()));
        accountPage.getAccountButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getProfileButton()));
        accountPage.getProfileButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), \"Billing\")]/..")));
        profilePage.getBillingSelector().click();
        profilePage.getBillingHistoryButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(), \"any billing\")]")));
        String text = driver.findElement(By.xpath("//h2[contains(text(), \"any billing\")]")).getText();
        Assertions.assertTrue(text.contains("You don’t have any billing history"));
    }

    @Test
    public void search(){
        mainPage.getSearchButton().click();
        mainPage.setSearchText("newsletter");
        mainPage.getSearchInputButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id = \"q\"]")));
        String searchText = driver.findElement(By.xpath("//input[@id = \"q\"]")).getAttribute("value");
        Assertions.assertTrue(searchText.contains("newsletter"));
    }

    @Test
    public void checkNotifications(){
        Config config = ConfigFactory.load("metadata.conf");
        String login = config.getString("userData.user.login");
        String password = config.getString("userData.user.password");
        mainPage.getLogInButton().click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));
        loginPage.setLoginText(login);
        loginPage.setPassword(password);
        loginPage.getLoginButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getAccountButton()));
        accountPage.getAccountButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getNotificationButton()));
        accountPage.getNotificationButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), \"No notification\")]")));
        String text = driver.findElement(By.xpath("//p[contains(text(), \"No notification\")]")).getText();
        Assertions.assertTrue(text.contains("No notifications right now"));
    }
}
