package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionClassDemo {

    WebDriver driver;
    String baseURL;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js= (JavascriptExecutor) driver;
        baseURL = "http://practice.automationtesting.in/my-account/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void performBrowserActions() {

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
     //   System.out.println(driver.getPageSource());
        driver.findElement(By.id("username")).sendKeys("pavanoltraining");
        driver.findElement(By.id("password")).sendKeys("Test@selenium123");

        WebElement element = driver.findElement(By.name("login"));
       // driver.findElement(By.name("login"));
       // element.click();
      //  js.executeScript("arguments[0].click()",element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
       // actions.sendKeys(Keys.ENTER).perform();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
