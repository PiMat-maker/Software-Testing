package AudiencePage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AudiencePage {
    private WebDriver driver;

    @FindBy(xpath = "//a[@aria-label = \"Explore Mailchimp pricing plans\"]")
    private WebElement pickPlanButton;

    public AudiencePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
