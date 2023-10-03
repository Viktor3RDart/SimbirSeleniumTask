package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.HomePageButtons;

import java.util.List;

import static helpers.TestData.*;

public class OpenAccountPage extends BaseStep {

    WebDriver driver;

    @FindBy(id = "userSelect")
    public WebElement customerNameField;

    @FindBy(id = "currency")
    public WebElement currencyField;

    @FindBy(css = "button[type=\"submit\"]")
    public WebElement processButton;

    @FindBy(xpath = "//*[@id=\"userSelect\"]/option")
    public List<WebElement> customersList;

    @FindBy(xpath = "//*[@id=\"currency\"]/option")
    public List<WebElement> currencyList;


    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Выбрать данные из дроп-дауна на экране Open Account")
    public void selectSomeDataInField(List<WebElement> list, String data) {
        HomePageButtons homePage = new HomePageButtons(driver);
        list.stream()
                .filter(element -> element.getText().equals(data))
                .forEach(element -> {
                    element.click();
                    homePage.openAccountButton.click();
                });
    }

    @Step("Добавить новый счет клиенту")
    public void openAccountForClient() {
        selectSomeDataInField(customersList, FULL_NAME);
        selectSomeDataInField(currencyList, CURRENCY);
        clickElement(processButton);
    }
}
