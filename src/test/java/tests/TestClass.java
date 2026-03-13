package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BookingPage;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.PurchasePage;
import utils.ExtentReportManager;

public class TestClass extends BaseTest {

	@DataProvider(name = "flightData")
    public Object[][] flightData() {
        return new Object[][] {
            { "Boston",   "London"  },
            { "Paris",    "Dublin"  },
            { "Portland", "New York"  }
        };
    }
	
    @Test(dataProvider = "flightData")
    public void bookFlight(String from ,String to) {
    	
    	ExtentReportManager.createTest("Book Flight");

        HomePage home = new HomePage(driver);
        BookingPage booking = new BookingPage(driver);
        PurchasePage purchase = new PurchasePage(driver);
        ConfirmationPage confirm = new ConfirmationPage(driver);

        // Step 1: Verify homepage loaded
        Assert.assertTrue(driver.getTitle().contains("BlazeDemo"),"Homepage did not load correctly");               
        System.out.println("[PASS] Step 1: Homepage loaded");

        // Steps 2 & 3: Select cities
        home.selectCity(from, to);
        System.out.println("[PASS] Steps 2 & 3: Cities selected");

        // Step 4: Click Find Flights
        home.clickFindFlight();
        System.out.println("[PASS] Step 4: Clicked Find Flights");

        // Step 5: Verify flights are listed
        Assert.assertTrue(booking.areFlightsDisplayed(),"No flights were displayed!");                
        System.out.println("[PASS] Step 5: Flights displayed");

        // Step 6: Choose a flight
        booking.chooseFlight();
        System.out.println("[PASS] Step 6: Flight chosen");

        // Step 7: Verify Purchase page is shown
        Assert.assertTrue(purchase.isPurchasePageDisplayed(),"Purchase page was not displayed!");                
        System.out.println("[PASS] Step 7: Purchase page displayed");

        // Steps 8, 9 & 10: Enter details and purchase
        purchase.enterDetails();
        System.out.println("[PASS] Steps 8, 9 & 10: Details entered and Purchase clicked");

        // Step 11: Verify confirmation
        Assert.assertEquals(confirm.msg(), "Thank you for your purchase today!","Booking confirmation message mismatch!");
                
        System.out.println("  Flight booking completed successfully!");
    }
    
    
      
}