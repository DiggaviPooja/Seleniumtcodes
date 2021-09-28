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

import java.util.List;

public class SelectClass {
    WebDriver driver;
    String baseURL;
    String baseURL1;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.opencart.com/index.php?route=account/register";
        baseURL1 = "https://www.orangehrm.com/orangehrm-30-day-trail/";
    }

    @Test
    public void DropDown()
    {
        driver.get(baseURL);

       WebElement dropDown = driver.findElement(By.xpath("//select[@name='country_id']"));

       Select dropBox = new Select(dropDown);
      // dropBox.selectByIndex(2);
       
       //selecting dropdown without using methods
     List<WebElement> allOptions = dropBox.getOptions();
     
     for(WebElement element : allOptions)
     {
         if(element.getText().equals("Cuba"));
         {
             element.click();
             break;
         }
     }

    }
    @Test
    public void multipleDropDowns()
    {
        driver.navigate().to(baseURL1);
        WebElement dropDown1 = driver.findElement(By.xpath(""));
        selectOptionFromDropDown(dropDown1,"that dropdown value");

        WebElement dropDown2 = driver.findElement(By.xpath(""));
        selectOptionFromDropDown(dropDown2,"that dropdown value");

        WebElement dropDown3 = driver.findElement(By.xpath(""));
        selectOptionFromDropDown(dropDown3,"that dropdown value");

    }

    public void selectOptionFromDropDown(WebElement ele, String value)
    {
        Select drp = new Select(ele);

        List<WebElement> allOptions1 = drp.getOptions();

        for(WebElement element : allOptions1)
        {
            if(element.getText().equals(value));
            {
                element.click();
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
