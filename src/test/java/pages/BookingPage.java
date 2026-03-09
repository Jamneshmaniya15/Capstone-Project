package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BookingPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "table.table tbody tr")
    List<WebElement> flightRows;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean areFlightsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.table tbody tr")));                
        System.out.println("[STEP] Flights found: " + flightRows.size());
        return flightRows.size() > 0;
    }

    public void chooseFlight() {
        WebElement firstFlight = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table.table tbody tr:first-child .btn-small")));               
        firstFlight.click();
        System.out.println("[STEP] Clicked 'Choose This Flight'");
    }
}