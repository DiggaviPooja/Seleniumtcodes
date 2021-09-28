package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BooStrapDropDown {

    WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        baseURL="https://www.hdfcbank.com/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void bootStrapDD()
    {

        WebElement productType =  driver.findElement(By.linkText("Select Product Type"));
        productType.click();

        List<WebElement> productTypes = driver.findElements(By.xpath("//ul[@class ='dropdown1 dropdown-menu']/li"));
        System.out.println("all options : " + productTypes.size());
        selectDropDown(productTypes, "Accounts");



    }

    public void selectDropDown(List<WebElement> element , String value)
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
    @AfterMethod
    public void tearDown() throws InterruptedException {
Thread.sleep(5000);
        driver.quit();
    }
}
