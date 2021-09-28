package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BrowserActions {
    WebDriver driver;
    String baseURL;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = "https://frontend.nopcommerce.com/";
    }

    @Test
    public void performBrowserActions() {
        driver.get(baseURL);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
