package com.saucedemo.utils;

import factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String path = "test-output/screenshots/" + fileName;
        
        try {
            File source = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);
            File destination = new File(path);
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return path;
    }
}