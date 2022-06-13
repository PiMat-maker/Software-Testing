package MailchimpPresentsPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class VideoPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[contains(text(), \"Please update your privacy settings to enable video play.\")]")
    private WebElement errorButton;

    public VideoPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
