package Misc;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class QRCOdeReading {
    static WebDriver driver;
    static String baseURL;
    public static void main(String[] args) throws IOException, NotFoundException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = "https://testautomationpractice.blogspot.com";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String qrCodeURL = driver.findElement(By.xpath("//div[@id='HTML4']//img")).getAttribute("src");

        URL url = new URL(qrCodeURL);

       BufferedImage bufferedImage = ImageIO.read(url);

        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        Result result =  new MultiFormatReader().decode(binaryBitmap);
        String text =result.getText();
        System.out.println(text);






    }
}
