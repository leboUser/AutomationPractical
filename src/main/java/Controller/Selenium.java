package Controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public  class Selenium extends TestListener {
    private WebDriver driver;

    public Selenium(String browsersType, boolean windowSize, String url, String testSuite, String testcase) throws Exception {
        super(testSuite,testcase);
        if (browsersType.toUpperCase().contentEquals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else if(browsersType.toUpperCase().contentEquals("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else if(browsersType.toUpperCase().contentEquals("EDGE")) {
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else {
            throw new Exception("unable to select browser");
        }


    }



    public void Webdriver(WebDriver driver){
        this.driver = driver;
    }
    public WebDriver driver(){
        return driver;
    }

    //Interacting Element
    private WebElement waitElement(By value){
        try {
            pause(1000);
            WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(value));
            WebElement element = driver.findElement(value);

            moveElement(element );

            return element;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.driver.findElement(value);
    }

    private void moveElement(WebElement element){
        Actions action = new Actions(driver());
        action.moveToElement(element);
        action.perform();
    }


    public void clickElement(By value ,boolean screenshot,String step)  {

        try {
            WebElement element;
            element = waitElement(value);
            element.click();

            if (screenshot) {
                TestWithScreenShot(true, driver(), step);
            } else {
                testPassOrFail(step, true,driver());
            }
        }catch (Exception e){
            e.getCause();
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage() , false,driver());
            }
        }
    }
    public void hoverElement(By value ,boolean screenshot,String step)  {

        try {
            WebElement element;
            element = waitElement(value);
            Actions action = new Actions(driver());
            action.moveToElement(element).pause(5);

            if (screenshot) {
                TestWithScreenShot(true, driver(), step);
            } else {
                testPassOrFail(step, true,driver());
            }
        }catch (Exception e){
            e.getCause();
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage() , false,driver());
            }
        }
    }

    public boolean selectElementByIndex(By value,int index){
        try {


            Select element = new Select(waitElement(value));
            element.selectByIndex(index);


            return true;

        }catch (Exception e){
            e.getCause();
            return false;
        }
    }

    public void enterText(By value, String textToEnter,Boolean screenshot,String step)  {
        try {


            WebElement element= waitElement(value);
            element.clear();
            element.sendKeys(textToEnter);

            if (screenshot) {
                TestWithScreenShot(true, driver(), step);
            } else {
                testPassOrFail(step, true,driver());
            }

        }catch (Exception e){

            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }


    public void validateElementText(By value,String textToValidate,Boolean screenshot,String step) {
        try {


            WebElement element = waitElement(value);
            if (screenshot) {
                TestWithScreenShot(element.getText().equals(textToValidate), driver(), step);
            } else {
                testPassOrFail(step, element.getText().equals(textToValidate),driver());
            }



        }catch (Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.toString());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }

    public String getTextFromElement(By value,boolean screenshot,String step) {
        try {

            WebElement element = waitElement(value);

            return element.getText();

        }catch (Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
            return null;
        }
    }

    public void GetlistElement(By value, String appear, Boolean screenshot, String step) {
        boolean result;
        try{

            waitElement(value);
            List<WebElement> elements = this.driver.findElements(value);
            for (WebElement elm:elements) {
                if( elm.getText().equals(appear)){
                    result =true;
                    if (screenshot) {
                        TestWithScreenShot(result, driver(), step);
                    } else {
                        testPassOrFail(step, result,driver());
                    }
                }
            }
        }catch(Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }





    public void pause(int milliseconds){
        try{
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean shutdown(){
        try {
            driver.close();
            driver.quit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            return false;
        }

    }

    protected String fileReader(String file) throws FileNotFoundException {

        File texts = new File(file);
        String text = "";
        Scanner myReader = new Scanner(texts);
        while(myReader.hasNext()){
                 text = myReader.nextLine();
                 }

        myReader.close();
        return text;
    }


}

