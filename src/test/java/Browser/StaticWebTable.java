package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class StaticWebTable
{

    WebDriver driver;
    String baseURL;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="https://www.selenium.dev/downlaods/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void webTable()
    {
        //1, find number of rows in a Table
        int rows = driver.findElements(By.xpath("//table[@class='data-list']/tbody/tr")).size();
        System.out.println("Total number of rows " +rows);

        //2. find number of columns
        int columns = driver.findElements(By.xpath("//table[@class='data-list']//thead/tr/th")).size();
        System.out.println("Total number of columns " +columns);

        //3. Retrieve specific row and column
        String value = driver.findElement(By.xpath("//table[@class='data-list']//tbody/tr[2]/td[1]")).getText();
        System.out.println("Get the value " + value);

        //4. Retrieve all the rows and columns from the tables
        for(int r = 1 ; r<=rows ; r++) // incrementation of rows
        {
            for(int c= 1; c<=columns; c++) // incrementation of columns
            {
                //parameterize the xpath
                String data = driver.findElement(By.xpath("//table[@class='data-list']//tbody/tr["+r+"]/td["+c+"]")).getText();
                System.out.print("Get the data " + data +"    ");
            }
            System.out.println();
        }

        //5. print the release date and version of Java language
        for(int r = 1; r<=rows; r++)
        {
            //parameterize the xpath
            String language = driver.findElement(By.xpath("//table[@class='data-list']//tbody/tr["+r+"]/td[1]")).getText();
            if(language.equals("java"))
            {
                String versionNo = driver.findElement(By.xpath("//table[@class='data-list']//tbody/tr["+r+"]/td[2]")).getText();
                String releaseDate = driver.findElement(By.xpath("//table[@class='data-list']//tbody/tr["+r+"]/td[3]")).getText();
                System.out.println(language+ " " +versionNo+ " " +releaseDate);
            }
        }


    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
Thread.sleep(3000);
        driver.quit();
    }
}
