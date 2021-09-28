package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InnerIframes {
    WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="http://demo.automationtesting.in/Frames.html";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() throws InterruptedException {

        WebElement innerFrameTab =  driver.findElement(By.xpath("//a[normalize-space()='Iframe with in an Iframe']"));
        innerFrameTab.click();


       WebElement outerFrame= driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
     //  outerFrame.click();
        driver.switchTo().frame(outerFrame);

        WebElement innerFrame= driver.findElement(By.xpath("//iframe[normalize-space()='<p>Your browser does not support iframes.</p>']"));
        //  outerFrame.click();
        driver.switchTo().frame(innerFrame);

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Welcome");




    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
