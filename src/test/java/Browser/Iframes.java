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

public class Iframes {

    WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() throws InterruptedException {
        driver.switchTo().frame("packageListFrame");
       WebElement firstFrame =  driver.findElement(By.xpath("//a[normalize-space()='org.openqa.selenium']"));
       firstFrame.click();
       driver.switchTo().defaultContent();
       Thread.sleep(3000);

        driver.switchTo().frame("packageFrame");
        WebElement secondFrame = driver.findElement(By.xpath("//span[normalize-space()='WebDriver']"));
        secondFrame.click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);

        driver.switchTo().frame("classFrame");
        WebElement thirdFrame = driver.findElement(By.xpath("//div[@class='topNav']//a[text()='Help']"));
        thirdFrame.click();

    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
