package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Elements;
import test.TestBase;

public class Page extends PageBase implements Elements {
	
    private Logger LOGGER = LogManager.getLogger(Page.class);
    protected WebDriver driver = TestBase.driver;
    private String movieName = "";
    private String directorList, writersList, starsList; String year;
    WebDriverWait wait = new WebDriverWait(driver, 2);
    
    public Page(String movieName) 
    {
    	this.movieName = movieName;
    }
     
	public void getMovieDetails() 
	{		
		driver.findElement(menuButtonId).click();
		LOGGER.info("Arama çubuðunun sol tarafýnda bulunan Menü butonuna basýlýr.");

		wait.until(ExpectedConditions.elementToBeClickable(oscarsXpath)).click();

        driver.findElement(dateXpath).click();
        LOGGER.info("“Event History” baþlýðý altýnda “1929” deðeri seçilir.");
  
        WebElement Element = driver.findElement(honoraryAwardXpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		LOGGER.info("Sayfa, Honorary Award'a kadar kaydýrýldý.");
		
		if(movieName.equals("The Circus"))
		{
			driver.findElement(theCircusXpath).click();
			LOGGER.info("The Circus seçilir.");
		}
		else if(movieName.equals("The Jazz Singer"))
		{
			driver.findElement(theJazzSingerXpath).click();
			LOGGER.info("The Jazz Singer seçilir.");
		}
		else{LOGGER.info("Bilinmeyen film.");}
        
		LOGGER.info("Film detay sayfasý açýldý.");
        
        directorList = driver.findElement(resultDirectorXpath).getText();
        writersList = driver.findElement(resultWritersXpath).getText();
        starsList = driver.findElement(resultStarsXpath).getText();
        year = driver.findElement(yearXpath).getText();
        LOGGER.info("Director, Writers ve Stars bilgileri kaydedildi.");
	}

	public void checkMovieDetails() 
	{
		driver.findElement(homeButtonId).click();
		LOGGER.info("Ekranýn sol üstünde bulunan “IMDb” butonuna týklanarak “Anasayfa’ya” dönülür.");

		driver.findElement(searchBoxId).sendKeys(movieName);
        LOGGER.info("Arama çubuðuna film ismi yazýlýr.");
        
        LOGGER.info("Sonuçlar arasýnda belirlenen filme týklanýr.");
        
        driver.findElement(By.id("iconContext-magnify")).click();
        
        String movieCredits = movieName+" "+ "(" + year + ")"; 
      
        if(movieCredits.equals(trimTitle(driver.findElement(titleText1Xpath).getText()))) {driver.findElement(titleClick1Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText2Xpath).getText()))) {driver.findElement(titleClick2Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText3Xpath).getText()))) {driver.findElement(titleClick3Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText4Xpath).getText()))) {driver.findElement(titleClick4Xpath).click();}
        else {LOGGER.info("Film bulunamadý.");}
        
        String Director_fullcredits = driver.findElement(resultDirector_fullCreditsXpath).getText();
        String Writers_fullcredits = driver.findElement(resultWriters_fullCreditsXpath).getText();
        String Stars_fullcredits = driver.findElement(resultStars_fullCreditsXpath).getText();

        if(directorList.equals(Director_fullcredits)) {LOGGER.info("Director bilgileri ayn.ý");}
        else {LOGGER.info("Director bilgileri ayný deðil.");}
        
        if(writersList.equals(Writers_fullcredits)) {LOGGER.info("Writer bilgileri ayný.");}
        else {LOGGER.info("Writer bilgileri ayný deðil.");}
        
        if(starsList.equals(Stars_fullcredits)) {LOGGER.info("Star bilgileri ayný.");}
        else {LOGGER.info("Star bilgileri ayný deðil.");} 
    }
        
	public void checkImages() 
	{
		WebElement Element = driver.findElement(photosXpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		LOGGER.info("Sayfa, Photos'a kadar kaydýrýldý.");

		driver.findElement(photosClickXpath).click();
		
		if(driver.findElement(noOfPhotosXpath).getText().contains("of")) 
		{
			do
			{  
				for (WebElement image : driver.findElements(By.cssSelector("img")))
				{
					isImageBroken(image);
				}
				driver.findElement(prevnextXpath).click();
				
			}while(!driver.findElement(prevnextXpath).getText().contains("Previous"));  
		}
		else
		{
			for (WebElement image : driver.findElements(By.cssSelector("img")))
			{
				isImageBroken(image);
			}
		}
		
		if(brokenImageCounter == 0) {LOGGER.info(imagesChecked+ " adet görsel tarandý; kýrýk link bulunmadý.");}
		else {LOGGER.info(imagesChecked+" adet görsel tarandý "+brokenImageCounter +" adet kýrýk link bulundu.");}
	}
}