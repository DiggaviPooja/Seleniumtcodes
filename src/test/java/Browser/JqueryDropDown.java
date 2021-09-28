package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class JqueryDropDown {
    static WebDriver driver;
    static String baseURL;

    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://jqueryscript.net/demo/Drop-Down-Combo-Tree/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       driver.findElement(By.id("justAnInputBox")).click();

      // selectChoiceValues(driver,"choice1");
        selectChoiceValues(driver,"choice 1","choice 2","choice 2 2");
      //  selectChoiceValues(driver,"all");
    }

    public static void selectChoiceValues(WebDriver driver,String... value) // to hold multiple or single values
    {
      List<WebElement> choiceList =driver.findElements(By.xpath("//span[@class='comboTreeItemTitle']")); // all the options inside the dropdwon
        if(!value[0].equalsIgnoreCase("all"))
        {
            for(WebElement item :choiceList)
            {
                String text = item.getText();
                for(String val : value)
                {
                    if(text.equals(val))
                    {
                        item.click();
                        break;
                    }
                }
            }
        }
        else
        {
            for(WebElement item : choiceList)
            {
                item.click();
            }
        }
    }
}
