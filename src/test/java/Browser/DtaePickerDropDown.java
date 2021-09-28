package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DtaePickerDropDown {

    WebDriver driver;
    String baseURL,date;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.dummyticket.com/dummy-ticket-for-visa-application/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void dropDownDatePicker()
    {


        driver.findElement(By.xpath("//input[@id='dob']")).click();

      Select month_drp = new Select( driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
      month_drp.selectByVisibleText("Oct");

        Select year_drp = new Select( driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
        year_drp.selectByVisibleText("1990");

        date = "10";
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
        for(WebElement ele : allDates)
        {
            String dt = ele.getText();
          //  System.out.println(dt);
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
