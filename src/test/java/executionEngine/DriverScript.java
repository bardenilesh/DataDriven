package executionEngine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverScript {

	private static WebDriver driver = null;
	public static String workDir = System.getProperty("user.dir");
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", workDir + "/drivers/chromedriver.exe");
		driver= new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://www.store.demoqa.com");
    driver.findElement(By.xpath(".//*[@id='account']/a")).click();
    driver.findElement(By.id("log")).sendKeys("testuser_3"); 
    driver.findElement(By.id("pwd")).sendKeys("Test@123");
    driver.findElement(By.id("login")).click();
//    driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
    driver.quit();
    }
}
