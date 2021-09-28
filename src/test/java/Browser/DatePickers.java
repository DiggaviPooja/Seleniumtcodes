package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DatePickers {

    WebDriver driver;
    String baseURL,year,month,date;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        baseURL="https://www.redbus.in/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
 @Test
 public void Calendar()
 {
        year = "2021";
        month = "July";
        date = "10";

                driver.findElement(By.id("onward_cal")).click();
        while(true)
        {
           String monthYear =  driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
           String arr[] = monthYear.split(" ");
           String mon = arr[0];
           String yr = arr[1];

           if(mon.equalsIgnoreCase(month) && yr.equals(year))

               break; // no need of braces if we have single statements
           else
               driver.findElement(By.xpath("//button[normalize-space()='>']")).click();

        }
        //Date Selection
        List<WebElement> allDates= driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//td"));
         for(WebElement ele : allDates)
         {
             String dt = ele.getText();
             if(dt.equals(date))
             {
                 ele.click();
                 break;
             }
         }


 }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
