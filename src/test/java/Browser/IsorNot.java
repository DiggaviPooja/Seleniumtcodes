package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IsorNot {

    WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://demo.nopcommerce.com/register";
    }

    @Test
    public void isActions()
    {
        driver.get(baseURL);
       WebElement searchBox = driver.findElement(By.id("small-searchterms"));
      System.out.println(searchBox.isDisplayed()) ;
        System.out.println(searchBox.isEnabled());

        WebElement radioButton = driver.findElement(By.cssSelector("#gender-male"));
        radioButton.click();
        System.out.println(radioButton.isSelected());

    }
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
