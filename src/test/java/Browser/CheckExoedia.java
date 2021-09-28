package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckExoedia {

    WebDriver driver;
    String baseURL,year,month,date;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.expedia.com/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(enabled=false)
    public void expediaAction() throws InterruptedException {
      WebElement flightTab=driver.findElement(By.xpath("//a[@href='?pwaLob=wizard-flight-pwa']"));
      flightTab.click();
        WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement wait=myWait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Leaving from']")));
        wait.sendKeys("New York");
        //wait.click(); // elementclickinterceptException

        List<WebElement> list=driver.findElements(By.xpath("//div[@class='truncate']/span/strong"));
        System.out.println("Size of Auto suggestion1:" +list.size());
        selectOption(list,"Islip (ISP - MacArthur)");

        WebElement goingTo=driver.findElement(By.xpath("//button[@aria-label='Going to']"));
        goingTo.sendKeys("Delhi");
        Thread.sleep(3000);

        List<WebElement> list1=driver.findElements(By.xpath("//div[@class='truncate']/span/strong"));
        System.out.println("Size of Auto suggestion2:" +list1.size());
        selectOption(list1,"Delhi (DEL - Indira Gandhi Intl.)");

    }
    
    public void selectOption(List<WebElement> element, String Value)
    {

        for(WebElement option:element)
        {
            if(option.getText().equals(Value))
            {
                System.out.println(option.getText());
                option.click();
                break;
            }
        }
    }

    @Test
    public void selectDate() throws InterruptedException {
        year = "2021";
        month = "December";
        date = "10";

        WebElement date1 = driver.findElement(By.id("d1-btn"));
        date1.click();
        Thread.sleep(3000);
        while (true) {
            String calendar1 = driver.findElement(By.xpath("//h2[normalize-space()='October 2021']")).getText();
            String arr[] = calendar1.split(" ");
            String mon = arr[0];
            String yr = arr[1];

            if (mon.equalsIgnoreCase(month) && yr.equals(year))
                break;
            else
                driver.findElement(By.xpath("//div[contains(@class,'uitk-calendar')]//button[2]")).click();
        }

        //Date selection
        List<WebElement> allDates=driver.findElements(By.xpath("//button[@type='button']"));

        for(WebElement ele:allDates)
        {
            String dt=ele.getText();
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
