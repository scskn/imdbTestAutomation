package test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	    public static WebDriver driver;
	    public WebDriverWait wait;
	    public Actions action;
	    private Logger LOGGER = LogManager.getLogger(TestBase.class);

	    @Before
	    public void beginTest()
	    {
	        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.imdb.com/");
	        
	        BasicConfigurator.configure();

	        LOGGER.info("https://www.imdb.com/ adresine gidilir");
	    }

	    @After
	    public void afterTest() throws InterruptedException
	    {
	    	driver.quit();
	    	LOGGER.info("Test tamamlandý");
	    }
}