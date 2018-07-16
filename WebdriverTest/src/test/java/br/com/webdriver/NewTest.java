package br.com.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.webdriver.config.DriverBase;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest extends DriverBase {
	
	private WebDriver driver;
  
	@Test(alwaysRun = true)
	public void primeiroTeste() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://g1.globo.com/");

        String search_text = "Google Search";
        WebElement search_button = driver.findElement(By.name("btnK"));

        String text = search_button.getAttribute("value");

        Assert.assertEquals(text, search_text, "Text not found!");
	}
 
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}