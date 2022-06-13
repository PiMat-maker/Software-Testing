package MailchimpPresentsPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class MailchimpPresentsPage {
    private WebDriver driver;

    @FindBy(xpath = "//li/span[@class = \"Eyebrow \"]")
    private List<WebElement> mediaTypeButtons;

    @FindBy(xpath = "//*[@id = \"homepage-grid-title\"]")
    private WebElement title;

    @FindBy(xpath = "//a[@class = \"ShowCard-show\"]")
    private List<WebElement> videos;

    public MailchimpPresentsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getTitleText(){
        return title.getText();
    }
}
