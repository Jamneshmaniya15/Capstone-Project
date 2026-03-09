package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    
    @BeforeSuite
    public void startReport() {
    	ExtentReportManager.getInstance();
    	System.out.println("[REPORT] Extent Report Initialized.");
    }
    
    @AfterSuite
    public void endReport() {
    	ExtentReportManager.flushReport();
    	System.out.println("[REPORT] Extent Report is Saved.");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        WebDriverManager.chromedriver().setup();

        if(browser.equals("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("[SETUP] Browser opened: Firefox");
        }else if (browser.equalsIgnoreCase("edge")) {  
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            System.out.println("[SETUP] Browser opened: Edge");
        }else {
        	driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("[SETUP] Browser opened: Chrome");
        }
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
    	
    	if(result.getStatus() == ITestResult.FAILURE) {
    		System.out.println("[FAILED] " +result.getName());
    		
    		String path = ScreenshotUtil.captureScreenshot(driver,result.getName());
    		System.out.println("[SCREENSHOT SAVED] " + path);
    	} else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().pass("Test PASSED ");

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.getTest().skip("Test SKIPPED ");
        }
    	
        if (driver != null) {
            driver.quit();
        }
    }
}