
package apTests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
///

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.amazon.in/");
        System.out.println("The Url is"+driver.getCurrentUrl());
        System.out.println("end Test case: testCase01");
    }

     public void testCase02() {
        System.out.println("Start Test case: testCase02");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.amazon.in/");
        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        search.sendKeys("laptop");
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER).perform();

        List<WebElement>lap=driver.findElements(By.xpath("//span[contains(text(),'Laptop')]"));
        for(int i=0;i<lap.size();i++){
            if(lap.get(i).getText().contains("Laptop")){
                System.out.println("Found laptop in a search="+i);
                break;
            }
        }
        System.out.println("end Test case: testCase02");
    }


     public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.amazon.in/");

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.body.style.zoom= '0.7'");
        Thread.sleep(2000);
       WebElement ele= driver.findElement(By.xpath("//*[text()=' Electronics ']"));
      
       js.executeScript("arguments[0].click() ", ele);
        String url= driver.getCurrentUrl();
        System.out.println("URL="+url);
        if(url.toLowerCase().contains("electronics")){
            System.out.println("Url coontains the electronics word--> Test Case is Passed");
        }else{
            System.out.println("Url do not contains electronics word --> Test Case Failed");
        }
        System.out.println("end Test case: testCase03");
    }

}

