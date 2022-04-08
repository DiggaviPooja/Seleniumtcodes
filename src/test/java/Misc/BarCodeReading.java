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
import java.net.URL;
import java.time.Duration;

public class BarCodeReading {

    static WebDriver driver;
    static String baseURL;

    public static void main(String[] args) throws IOException, NotFoundException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = "https://testautomationpractice.blogspot.com";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


       String barCodeURL= driver.findElement(By.xpath("//div[@id='HTML12']//img[1]")).getAttribute("src");
        System.out.println(barCodeURL);

        URL url = new URL(barCodeURL);

        BufferedImage bufferedImage = ImageIO.read(url);

        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        Result result =  new MultiFormatReader().decode(binaryBitmap);//result contains actual text
        String text =result.getText();
        System.out.println(text);

    }
}
