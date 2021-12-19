package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.TestBase;

public class PageBase {
	
	 protected int brokenImageCounter = 0;
	 protected int imagesChecked = 0;
	 protected WebDriver driver = TestBase.driver;
	 private Logger LOGGER = Logger.getLogger(PageBase.class);
	 
	 protected String trimTitle(String titleToTrim) 
	 {
		if(titleToTrim.contains("aka")) 
		{
			String yearInfo = titleToTrim.substring(titleToTrim.indexOf("(")+1, titleToTrim.indexOf(")"));
			String newTitleForm = (titleToTrim.substring(titleToTrim.lastIndexOf(")") + 6));
			String ultimateTitleForm = newTitleForm.substring(1, newTitleForm.length() - 1);		
			return "" +ultimateTitleForm + " (" +yearInfo + ")";	
		}
		else {return titleToTrim;}
	 }
	
	 protected int isImageBroken(WebElement image)
	 {
		imagesChecked++;
	    if (image.getAttribute("naturalWidth").equals("0"))
	    {
	    	LOGGER.info(image.getAttribute("outerHTML") + " kýrýk.");
	        brokenImageCounter++;
	    }
	    return brokenImageCounter; 		
	 }
}