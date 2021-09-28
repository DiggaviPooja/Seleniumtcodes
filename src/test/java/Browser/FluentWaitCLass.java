package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitCLass {

    private WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.google.com/";
        driver.manage().window().maximize();
    }

    @Test
    public void isActions()
    {
        driver.get(baseURL);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("abcd");

     //   WebElement searchButton = driver.findElement(By.name("btnk"));
        searchBox.sendKeys(Keys.ENTER);

        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

     /*   WebElement linkedText = fluentWait.until(new Function(WebDriver,WebElement) {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.linkText("ABC Song for children | ABCD Alphabet Song - YouTube"));
            }*/

        WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement linkedText = driver.findElement(By.xpath("//a[@data-hveid='CAEQAw']"));
                if(linkedText.isEnabled())
                {
                    System.out.println("Element Found");
                }

                return linkedText;
            }
        });
                element.click();

    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
