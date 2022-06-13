package AccountPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AccountPage {
    private WebDriver driver;

    @FindBy(xpath = "//img[contains(@alt, \"Signed in\")]")
    private WebElement accountImage;

    @FindBy(xpath = "//img[contains(@alt, \"Signed in\")]/../../..")
    private WebElement accountButton;

    @FindBy(xpath = "//a[contains(text(), \"Profile\")]")
    private WebElement profileButton;

    @FindBy(xpath = "//*[@id = \"notifications\"]/button")
    private WebElement notificationButton;

    public AccountPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
