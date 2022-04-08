package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class TestWindow {
    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.naukri.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String parentWindow = driver.getWindowHandle();

        WebElement openLink = driver.findElement(By.xpath("//div[normalize-space()='Register']"));
        openLink.click();


        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows);//displays two as it has currently two opened windows

        Iterator<String> itr = windows.iterator();
        Iterator<String> itr1 = null;
        int i = 0;

        while (itr.hasNext()) {
            System.out.println(i);
            String window = itr.next();

            driver.switchTo().window(window);
            System.out.println("title in register page: " + driver.getTitle());
            i++; //to check how many times loop executes

            if (driver.getTitle().equals("Register on Naukri.com: Apply to Millions of Jobs Online")) {
                System.out.println("in register page");
                WebElement clickOn = driver.findElement(By.xpath("//a[normalize-space()='FAQs']"));
                clickOn.click();
                Thread.sleep(5000);
                Set<String> windows1 = driver.getWindowHandles();
                System.out.println(windows1);//displays three as it has currently three opened windows
                itr1 = windows1.iterator();
                //driver.close();
                // break;
                //Frequently Asked Questions- Jobseeker – Naukri.com
            }
        }
        int j = 0;
        while (itr1.hasNext()) {
            System.out.println(j);
            String window1 = itr1.next();
            driver.switchTo().window(window1).getTitle();
            System.out.println(driver.switchTo().window(window1).getTitle());
            j++;

            if (driver.getTitle().equals("Register on Naukri.com: Apply to Millions of Jobs Online")) {

                System.out.println("in register page");
            } else if (driver.getTitle().equals("Frequently Asked Questions- Jobseeker – Naukri.com")) {
                System.out.println("in FAQ page");
                driver.findElement(By.xpath("//a[text()='Recruiter']")).click();
                //  driver.close();
                //  break;
            }
        }







        String parentTitle = driver.switchTo().window(parentWindow).getTitle();
        System.out.println("parent title" +parentTitle);



    }
}