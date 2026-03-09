package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;  // ✅ ADD THIS

    @FindBy(name = "fromPort")
    WebElement fromCity;

    @FindBy(name = "toPort")
    WebElement toCity;

    @FindBy(css = "input[type='submit']")
    WebElement findFlightBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ ADD THIS
        PageFactory.initElements(driver, this);
    }

    public void selectCity(String from, String to) {
        // ✅ FIX: Wait for dropdowns to be visible before selecting
        wait.until(ExpectedConditions.visibilityOf(fromCity));
        wait.until(ExpectedConditions.visibilityOf(toCity));

        new Select(fromCity).selectByVisibleText(from);
        new Select(toCity).selectByVisibleText(to);
        System.out.println("[STEP] Selected cities: " + from + " → " + to);
    }

    public void clickFindFlight() {
        findFlightBtn.click();
        System.out.println("[STEP] Clicked 'Find Flights'");
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
