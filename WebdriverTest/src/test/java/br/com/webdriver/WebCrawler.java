package br.com.webdriver;

import java.util.ArrayList;
import java.util.List;
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
import br.com.webdriver.model.Noticias;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebCrawler extends DriverBase {
	
	private WebDriver driver;
	private List<WebElement> listItens = new ArrayList<WebElement>(); 
	private List<Noticias> listNoticias = new ArrayList<Noticias>();
  
	@Test(alwaysRun = true)
	public void primeiroTeste() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://g1.globo.com");
        int cont = 0;

        WebElement search = driver.findElement(By.id("busca-campo"));
        
        search.clear();
        search.sendKeys("ROBÔS");
        
        search.submit();
        
        Thread.sleep(7200);
        
        WebElement result = driver.findElement(By.cssSelector("#content > div > div > ul"));
        List<WebElement> resultList = result.findElements(By.tagName("li"));
        
        for (WebElement item : resultList) {
    		listItens.add(item);
    		
    		if(cont>3)
    			break;
    		
    		cont++;
        }
        
        if(!listItens.isEmpty()) {
        	for (WebElement item : listItens) {
        		Noticias noticia = new Noticias();
        		
        		WebElement titulo = item.findElement(By.tagName("a"));
        		
        		noticia.setTitulo(titulo.getText());
        		noticia.setLink(titulo.getAttribute("href"));
        		
        		listNoticias.add(noticia);
        	}
        }
        	Assert.assertTrue(true);

       // Assert.assertEquals("asdas", "asda", "Text not found!");
	}
 
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}