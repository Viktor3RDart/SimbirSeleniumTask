package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageButtons {

    private final WebDriver driver;

    @FindBy(css = "button[ng-click=\"addCust()\"]")
    private WebElement addCustomerButton;

    @FindBy(css = "button[ng-click=\"openAccount()\"]")
    private WebElement openAccountButton;

    @FindBy(css = "button[ng-click=\"showCust()\"]")
    private WebElement customersButton;


    public HomePageButtons(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }

    public void setAddCustomerButton(WebElement addCustomerButton) {
        this.addCustomerButton = addCustomerButton;
    }

    public WebElement getOpenAccountButton() {
        return openAccountButton;
    }

    public void setOpenAccountButton(WebElement openAccountButton) {
        this.openAccountButton = openAccountButton;
    }

    public WebElement getCustomersButton() {
        return customersButton;
    }

    public void setCustomersButton(WebElement customersButton) {
        this.customersButton = customersButton;
    }
}
