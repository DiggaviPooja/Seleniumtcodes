package Misc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Base64;

public class EncodeDecode {

    static WebDriver driver;
    static String baseURL;

    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = "https://demo.nopcommerce.com/login";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.linkText("Log in")).click();

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("pavanoltraining@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(decodedSting("dGVzdDEyMw=="));

        driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
    }

    public static String decodedSting(String password)
    {
        byte[] decodedString =  Base64.getDecoder().decode(password);

        return (new String(decodedString));

    }
}
