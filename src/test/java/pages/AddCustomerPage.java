package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.TestData.*;

public class AddCustomerPage extends BaseStep {

    WebDriver driver;

    @FindBy(css = "input[placeholder=\"First Name\"]")
    public WebElement firstNameField;

    @FindBy(css = "input[placeholder=\"Last Name\"]")
    public WebElement lastNameField;

    @FindBy(css = "input[placeholder=\"Post Code\"]")
    public WebElement postCodeField;

    @FindBy(css = "button[type=\"submit\"]")
    public WebElement addCustomerButton;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Полный путь создания клиента без счета")
    public void createClient() {
        sendKeys(firstNameField, FIRST_NAME);
        sendKeys(lastNameField, LAST_NAME);
        sendKeys(postCodeField, POST_CODE);
        clickElement(addCustomerButton);
    }
}
