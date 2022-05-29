package Controller;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TestListener extends Report  {

    private ExtentTest test;
    private String testCase;
    private int screenCounter = 0;


    public TestListener(String testCases,String testSuite) {
        super(testSuite+"\\"+testCases);
        this.testCase = testCases;
        test = report.createTest(testCase);
    }


    public void testPassOrFail(String reasonP_F, boolean result, WebDriver driver)  {
        try{
            if (result) {
                this.test.pass(reasonP_F);

            } else {
                this.test.fail(reasonP_F);
            }
        }catch(Exception e){
            flush();
            driver.close();
            driver.quit();
        }
    }

    public void TestWithScreenShot(boolean condition, WebDriver driver,String reasonP_F)  {
        try {
            if(condition) {
                test.pass(reasonP_F, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(condition,reportDirctory,driver)).build());

            }else{
                test.fail(reasonP_F,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(condition,reportDirctory,driver)).build());
            }
        } catch (IOException  e) {
            flush();
            driver.close();
            driver.quit();
        }
    }


    public void testStepPassOrFail(boolean condition,String description){
        if(condition){
            test.pass(description);
        }else{
            test.fail(description);
        }
    }

    protected String takeScreenshot(Boolean status, String getReportDirectory, WebDriver driver) throws IOException {
        screenCounter++;
        String imagePath =getReportDirectory;
        String relativePath = "Screenshots\\";

        new File(imagePath + relativePath).mkdirs();

        relativePath=relativePath+screenCounter + "_";
        if (status) {
            relativePath=relativePath+"PASSED";
        } else {
            relativePath = relativePath+"FAILED";
        }
        relativePath=relativePath+".png";

        imagePath=imagePath+relativePath;
        File screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotBase64,new File(imagePath));

        return imagePath;

    }

}