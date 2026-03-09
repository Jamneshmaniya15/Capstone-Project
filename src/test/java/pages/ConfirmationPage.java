package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By confirmationMsg = By.tagName("h1");
	
	public String msg() {
		return driver.findElement(confirmationMsg).getText();
	}
}
