package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Navigation {
    WebDriver driver;
    String baseURL;
    String baseURL2;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.snapdeal.com";
        baseURL2= "https://www.amazon.com";
    }

    @Test
    public void isNavigated()
    {
        driver.get(baseURL);
        //driver.get(baseURL2); // or
        driver.navigate().to(baseURL2); // interally calls get Method so get and navigateTo both are same
        driver.navigate().back();
        driver.navigate().forward();

        driver.navigate().refresh();
    }
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
