package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowHandlesTestNaukri {

    WebDriver driver;
    String baseURL;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL="http://www.naukri.com/";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void windowHandling() throws InterruptedException {
        //getWIndowHandle
        String parentID = driver.getWindowHandle();
        System.out.println("Parent Window ID: " +parentID);

        driver.findElement(By.xpath("//div[normalize-space()='Register']")).click();
        //getwindowhandles // get all handles
       Set<String> windoIds = driver.getWindowHandles();
       //first Method -Iterator()
//       Iterator<String> iterator = windoIds.iterator();
//        String parentWindowID = iterator.next();
//        String childWindowID = iterator.next();
//
//        System.out.println("Parent Window ID2: " +parentWindowID);
//        System.out.println("child Window ID: " +childWindowID);

        //another method from anil tomar
        //switching between handles
        for(String handle : windoIds)
        {
            System.out.println(handle);
            if(!handle.equals(parentID))
            {
                driver.switchTo().window(handle);
                Thread.sleep(2000);
                String display = driver.findElement(By.cssSelector(".global-title-1")).getText();
                System.out.println(display);
                driver.close();
                break;
               // driver.switchTo().window(handle);

            }
        }

        //switch back to the parent window
        String parentTitle = driver.switchTo().window(parentID).getTitle();
        System.out.println(parentTitle);


        //Second method
        //convert set into list
   //     List<String> windowsIdsList = new ArrayList<>(windoIds); //*

      /*  String parentWIndowID = windowsIdsList.get(0);
        String childWIndowID = windowsIdsList.get(1);

        System.out.println("Parent Window ID2: " +parentWIndowID);
        System.out.println("Parent Window ID: " +childWIndowID);

        //switch
        driver.switchTo().window(parentWIndowID);
        System.out.println("Parent Window title: " +driver.getTitle());

        driver.switchTo().window(childWIndowID);
        System.out.println("Child Window title: " +driver.getTitle());*/

        //for each loop
       /* for(String winId :windowsIdsList)
        {
            System.out.println(winId);
           String title = driver.switchTo().window(winId).getTitle();
            System.out.println(title);
        }*/

  /*      for(String winId :windowsIdsList) //*
        {
            String title = driver.switchTo().window(winId).getTitle();
            if(title.equals("OrangeHRM"))
            {
             //   driver.close();
                //do whatever you want to do in that window
                System.out.println(title);
            }
        }*/

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit(); //closes all the browser windows
       // driver.close(); // closes single browser window which is pinned
    }
}
