package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CustomersPage extends BaseStep {

    WebDriver driver;

    @FindBy(css = "a[ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    public WebElement firstNameTableTitle;

    @FindBy(css = "a[ng-click=\"sortType = 'lName'; sortReverse = !sortReverse\"]")
    public WebElement lastNameTableTitle;

    @FindBy(css = "a[ng-click=\"sortType = 'postCd'; sortReverse = !sortReverse\"]")
    public WebElement postCodeTableTitle;

    @FindBy(xpath = "//*[contains(text(), \"Account Number\")]")
    public WebElement accountNumberTableTitle;

    @FindBy(xpath = "//*[contains(text(), \"Delete Customer\")]")
    public WebElement deleteCustomerTableTitle;

    @FindBy(css = "input[ng-model=\"searchCustomer\"] ")
    public WebElement searchCustomerField;

    @FindBy(css = "td:nth-child(1)")
    public List<WebElement> firstNameList;

    @FindBy(css = "td:nth-child(2)")
    public List<WebElement> lastNameList;

    @FindBy(css = "td:nth-child(3)")
    public List<WebElement> postCodeList;

    @FindBy(css = "td:nth-child(4)")
    public List<WebElement> accountNumberList;

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Отсортировать список по порядку 'от А до Я'")
    public ArrayList<String> sortListNaturalOrder(ArrayList<String> list) {
        list.sort(Comparator.naturalOrder());
        return new ArrayList<>(list);
    }

    @Step("Отсортировать список по порядку 'от Я до А'")
    public ArrayList<String> sortListReverseOrder(ArrayList<String> list) {
        list.sort(Comparator.reverseOrder());
        return new ArrayList<>(list);
    }
}
