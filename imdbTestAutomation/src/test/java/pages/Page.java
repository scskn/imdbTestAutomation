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
		LOGGER.info("Arama �ubu�unun sol taraf�nda bulunan Men� butonuna bas�l�r.");

		wait.until(ExpectedConditions.elementToBeClickable(oscarsXpath)).click();

        driver.findElement(dateXpath).click();
        LOGGER.info("�Event History� ba�l��� alt�nda �1929� de�eri se�ilir.");
  
        WebElement Element = driver.findElement(honoraryAwardXpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		LOGGER.info("Sayfa, Honorary Award'a kadar kayd�r�ld�.");
		
		if(movieName.equals("The Circus"))
		{
			driver.findElement(theCircusXpath).click();
			LOGGER.info("The Circus se�ilir.");
		}
		else if(movieName.equals("The Jazz Singer"))
		{
			driver.findElement(theJazzSingerXpath).click();
			LOGGER.info("The Jazz Singer se�ilir.");
		}
		else{LOGGER.info("Bilinmeyen film.");}
        
		LOGGER.info("Film detay sayfas� a��ld�.");
        
        directorList = driver.findElement(resultDirectorXpath).getText();
        writersList = driver.findElement(resultWritersXpath).getText();
        starsList = driver.findElement(resultStarsXpath).getText();
        year = driver.findElement(yearXpath).getText();
        LOGGER.info("Director, Writers ve Stars bilgileri kaydedildi.");
	}

	public void checkMovieDetails() 
	{
		driver.findElement(homeButtonId).click();
		LOGGER.info("Ekran�n sol �st�nde bulunan �IMDb� butonuna t�klanarak �Anasayfa�ya� d�n�l�r.");

		driver.findElement(searchBoxId).sendKeys(movieName);
        LOGGER.info("Arama �ubu�una film ismi yaz�l�r.");
        
        LOGGER.info("Sonu�lar aras�nda belirlenen filme t�klan�r.");
        
        driver.findElement(By.id("iconContext-magnify")).click();
        
        String movieCredits = movieName+" "+ "(" + year + ")"; 
      
        if(movieCredits.equals(trimTitle(driver.findElement(titleText1Xpath).getText()))) {driver.findElement(titleClick1Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText2Xpath).getText()))) {driver.findElement(titleClick2Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText3Xpath).getText()))) {driver.findElement(titleClick3Xpath).click();}
        else if(movieCredits.equals(trimTitle(driver.findElement(titleText4Xpath).getText()))) {driver.findElement(titleClick4Xpath).click();}
        else {LOGGER.info("Film bulunamad�.");}
        
        String Director_fullcredits = driver.findElement(resultDirector_fullCreditsXpath).getText();
        String Writers_fullcredits = driver.findElement(resultWriters_fullCreditsXpath).getText();
        String Stars_fullcredits = driver.findElement(resultStars_fullCreditsXpath).getText();

        if(directorList.equals(Director_fullcredits)) {LOGGER.info("Director bilgileri ayn.�");}
        else {LOGGER.info("Director bilgileri ayn� de�il.");}
        
        if(writersList.equals(Writers_fullcredits)) {LOGGER.info("Writer bilgileri ayn�.");}
        else {LOGGER.info("Writer bilgileri ayn� de�il.");}
        
        if(starsList.equals(Stars_fullcredits)) {LOGGER.info("Star bilgileri ayn�.");}
        else {LOGGER.info("Star bilgileri ayn� de�il.");} 
    }
        
	public void checkImages() 
	{
		WebElement Element = driver.findElement(photosXpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		LOGGER.info("Sayfa, Photos'a kadar kayd�r�ld�.");

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
		
		if(brokenImageCounter == 0) {LOGGER.info(imagesChecked+ " adet g�rsel tarand�; k�r�k link bulunmad�.");}
		else {LOGGER.info(imagesChecked+" adet g�rsel tarand� "+brokenImageCounter +" adet k�r�k link bulundu.");}
	}
}