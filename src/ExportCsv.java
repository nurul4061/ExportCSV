import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ExportCsv {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        Thread.sleep(3000);

        WebElement dropDownDriver = driver.findElement(By.id("nav-flyout-shopAll")); // Limiting Webdriver scope

        FileWriter fWriter = new FileWriter("C:\\Work\\link3.csv");
        fWriter.append("Title");
        fWriter.append(',');
        fWriter.append("URL");
        fWriter.append('\n');

        List < WebElement > dropDownLinks = dropDownDriver.findElements(By.tagName("a"));

        System.out.println(dropDownLinks.size()); //Print the dropDownLinks size

        for (WebElement link: dropDownLinks) {

            String element1 = link.findElement(By.tagName("span")).getText();
            String element2 = link.getAttribute("href");

            System.out.println(element1);
            System.out.println(element2);
            fWriter.append(element1);
            fWriter.append(',');
            fWriter.append(element2);
            fWriter.append('\n');
			fWriter.flush();
			
        }


        WebElement navMenuDriver = driver.findElement(By.xpath("//*[@id=\"nav-main\"]/div[3]"));

        List < WebElement > navMenuLinks = navMenuDriver.findElements(By.tagName("a"));

        System.out.println(navMenuLinks.size());

        for (WebElement link: navMenuLinks) {
            if (link.isDisplayed()) {

                String element1 = link.getText();
                String element2 = link.getAttribute("href");

                System.out.println(element1);
                System.out.println(element2);
                fWriter.append(element1);
                fWriter.append(',');
                fWriter.append(element2);
                fWriter.append('\n');
				fWriter.flush();
				
            }


        }

        driver.quit();

    }

}