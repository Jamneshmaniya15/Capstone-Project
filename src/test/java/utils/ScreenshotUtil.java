package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // Timestamped filename so screenshots never overwrite each other
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "reports/screenshots/";
        String filePath = screenshotDir + testName + "_" + timestamp + ".png";

        try {
            Files.createDirectories(Paths.get(screenshotDir));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            System.out.println("[SCREENSHOT] Saved: " + filePath);
        } catch (IOException e) {
            System.out.println("[SCREENSHOT ERROR] " + e.getMessage());
        }

        return filePath;
    }
}