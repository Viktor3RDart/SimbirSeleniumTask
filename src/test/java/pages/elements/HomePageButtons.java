package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageButtons {

    WebDriver driver;

    @FindBy(css = "button[ng-click=\"addCust()\"]")
    public WebElement addCustomerButton;

    @FindBy(css = "button[ng-click=\"openAccount()\"]")
    public WebElement openAccountButton;

    @FindBy(css = "button[ng-click=\"showCust()\"]")
    public WebElement customersButton;

    public HomePageButtons(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
