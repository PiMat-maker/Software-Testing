package MainPage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class MainPage {
    private WebDriver driver;

    @FindBy(xpath = "//li/a[@href=\"https://login.mailchimp.com/\"]")
    private WebElement logInButton;

    @FindBy(xpath = "//a[contains(text(), \"Check it out\")]")
    private WebElement filmsButton;

    @FindBy(xpath = "//li/div/select[@data-behavior = \"languagePicker\"]")
    private WebElement languageSelector;

    @FindBy(xpath = "//li/div/select[@data-behavior = \"languagePicker\"]/option")
    private List<WebElement> languages;

    @FindBy(xpath = "//a[@aria-label = \"Win repeat business with predictive segmentation\"]")
    private WebElement winRepeatBusinessButton;

    @FindBy(xpath = "//a[@data-behavior = \"search-overlay\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id = \"actionable-search-drawer-input\"]")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@aria-label = \"Search Mailchimp\"]")
    private WebElement searchInputButton;



    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setSearchText(String text){
        searchInput.sendKeys(text);
    }

}
