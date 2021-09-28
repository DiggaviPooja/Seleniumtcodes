package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

public class JSDemo {

    WebDriver driver;
    String baseUrl;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUP()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        baseUrl="https://www.nopcommerce.com/en/demo";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void test()
    {
        WebElement element = driver.findElement(By.xpath("//a[@class='mobile-logo']"));
        js.executeScript("arguments[0].style.border='3px Solid red'",element);
      //  js.executeScript("arguments[0].click;",element);
      //   js.executeScript("return document.title;",element);




    }



    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
