package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PurchasePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "inputName")           
    WebElement name;

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "zipCode")
    WebElement zipCode;

    @FindBy(id = "cardType")
    WebElement cardType;

    @FindBy(id = "creditCardNumber")
    WebElement creditCardNumber;

    @FindBy(id = "creditCardMonth")
    WebElement creditCardMonth;

    @FindBy(id = "creditCardYear")
    WebElement creditCardYear;

    @FindBy(id = "nameOnCard")
    WebElement nameOnCard;

    @FindBy(css = "input[value='Purchase Flight']")
    WebElement purchaseFlightBtn;

    @FindBy(css = "h2")
    WebElement pageHeading;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isPurchasePageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        String heading = pageHeading.getText();
        System.out.println("[STEP] Purchase page heading: " + heading);
        return heading.contains("Your flight from");
    }

    public void enterDetails() {
        // Personal details
        name.sendKeys("Jamnesh Maniya");
        address.sendKeys("Street 23");
        city.sendKeys("Mumbai");
        state.sendKeys("Maharashtra");
        zipCode.sendKeys("200020");

        // Payment details
        new Select(cardType).selectByVisibleText("Visa");
        creditCardNumber.sendKeys("4111111111111111");
        creditCardMonth.clear();
        creditCardMonth.sendKeys("12");
        creditCardYear.clear();
        creditCardYear.sendKeys("2027");
        nameOnCard.sendKeys("Jamnesh Maniya");

        System.out.println("[STEP] Personal and payment details entered");

        wait.until(ExpectedConditions.elementToBeClickable(purchaseFlightBtn));
        purchaseFlightBtn.click();
        System.out.println("[STEP] Clicked 'Purchase Flight'");
    }
}