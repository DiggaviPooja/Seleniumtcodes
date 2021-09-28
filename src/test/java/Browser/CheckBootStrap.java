package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CheckBootStrap {

    static WebDriver driver;
    static String baseURL;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = "https://www.hdfcbank.com/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement productType = driver.findElement(By.linkText("Select Product Type"));
        productType.click();

        List<WebElement> productTypes = driver.findElements(By.xpath("//ul[@class ='dropdown1 dropdown-menu']/li"));
        System.out.println("all options : " + productTypes.size());
        selectDropDown(productTypes, "Accounts");
    }
    public static void selectDropDown(List<WebElement> element , String value)
    {

        for(WebElement products : element)
        {
            if(products.getText().equals(value))
            {
                products.click();
                break;
            }
        }
    }
}
