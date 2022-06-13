package AccountPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProfilePage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), \"Billing\")]/..")
    private WebElement billingSelector;

    @FindBy(xpath = "//a[contains(text(), \"Billing history\")]")
    private WebElement billingHistoryButton;

    public ProfilePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
