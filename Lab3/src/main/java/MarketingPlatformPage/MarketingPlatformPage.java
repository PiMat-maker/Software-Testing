package MarketingPlatformPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class MarketingPlatformPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class = \"margin--bottom-c3\"]/a[contains(text(), \"Sign Up Free\")]")
    private WebElement signUpFreeButton;

    @FindBy(xpath = "//select[@id = \"all_plans_contacts_select\"]")
    private WebElement contactsAmountSelector;

    @FindBy(xpath = "//select[@id = \"all_plans_contacts_select\"]/option")
    private List<WebElement> contactsAmounts;

    public MarketingPlatformPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
