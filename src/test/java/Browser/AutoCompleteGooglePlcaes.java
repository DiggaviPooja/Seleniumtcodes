package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class AutoCompleteGooglePlcaes {


    WebDriver driver;
    String baseURL;
    String text;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        baseURL="https://www.twoplugs.com/newsearchserviceneed";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void autoComplete() throws InterruptedException {

        WebElement searchBox = driver.findElement(By.id("autocomplete"));
        searchBox.clear();
        searchBox.sendKeys("Toronto");

        do {
            searchBox.sendKeys(Keys.ARROW_DOWN);
            text = searchBox.getAttribute("value");
            if(text.equals("Toronto NSW, Australia"))
            {
                searchBox.sendKeys(Keys.ENTER);
                break;
            }
            Thread.sleep(3000);
        }while ((!text.isEmpty()));

     /*   while(!text.isEmpty())   // got it why while did not use? because of text"String.isEmpty()" because "this.text" is null"
        {
            searchBox.sendKeys(Keys.ARROW_DOWN);
            text = searchBox.getAttribute("value");
            if(text.equals("Toronto NSW, Australia"))
            {
                searchBox.sendKeys(Keys.ENTER);
                break;
            }
        }*/
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
