package DHL_pack;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC026_outboundsingle_pre2hr {
	
    
@Test
public void f() throws InterruptedException, IOException {

	
		String order = UtilityMethods.timestampedordernum();
		
	    WebDriver driver = UtilityMethods.driverinitialize();
	    driver.get("https://spl-test.dhl.com/portal/home/");
	  	UtilityMethods.login(driver);	
	  	WebElement e1 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'OMS']")));
		e1.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame5");
		 //SELECT CUSTOMER 
		Select dropdown1 = new Select(driver.findElement(By.id("customer")));
		dropdown1.selectByValue("RIO_GLOBAL");
		Thread.sleep(3000);
	     //SELECT CUSTOMER SERVICE TYPE
		Select dropdown2 = new Select(driver.findElement(By.id("customerServiceType")));
		dropdown2.selectByValue("Simple Outbound");
		 //SELECT REQUESTER ID
		driver.findElement(By.id("requestorID")).sendKeys("rio_global_edi_requestor", Keys.TAB);
		Thread.sleep(1000);
	     // SELECT SERVICE LEVEL 	
		Select dropdown3 = new Select(driver.findElement(By.id("serviceLevel")));
		dropdown3.selectByValue("PRE 2 HR");
		Thread.sleep(1000);
		 //SELECT ALLOCATION RULE
		Select dropdown4 = new Select(driver.findElement(By.id("allocationRuleId")));
		dropdown4.selectByValue("SC_ALL");
		 //SELECT SHIP TO
		WebElement shipto =(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("shipfromWH")));
		shipto.sendKeys("MWRGWHS01", Keys.TAB);
		  //SELECT CUSTOMER ORDER
		driver.findElement(By.id("ref1")).click();
		driver.findElement(By.id("ref1")).sendKeys(order,Keys.TAB);
	      //SELECT SAP ORDER NUMBER
		driver.findElement(By.id("ref4")).sendKeys(order,Keys.TAB);
		  //SELECT COLLECTION SITE
		driver.findElement(By.id("shipToID")).sendKeys("RIO_SITE_1",Keys.TAB);
		  //SELECT PART NUMBER
		 driver.findElement(By.id("partNumber_0")).click();
	    driver.findElement(By.id("partNumber_0")).sendKeys("NORMAL-04",Keys.TAB);
	      //SELECT PRODUCT CLASS
	    Select dropdown5 = new Select(driver.findElement(By.id("productClass_0")));
		dropdown5.selectByValue("GOOD");
		  //SELECT UOM
		Select dropdown6 = new Select(driver.findElement(By.id("uom_0")));
		dropdown6.selectByValue("EACH");
		  //SELECT QUANTITY
		driver.findElement(By.id("orderCaptureFB_orderLines_0__quantity")).sendKeys("2",Keys.TAB);	
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		driver.switchTo().frame("frame5");
		  //CLICK CONFIRM
	    driver.findElement(By.id("confirmBtn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("confirmBtn")).click();
	     //Assertion Check for Order Number ::
	    WebElement e2 = (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+order+"')]")));
	   	System.out.println(e2.isDisplayed());
		Assert.assertTrue(e2.isDisplayed());
		WebElement dhlordernum =(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'110')]")));
		String order2 = dhlordernum.getText();
		OrderNumVariables.DHL_Ordernum[1] = order2;
		System.out.println("DHL_Ordernum[1] = "+order2);
		driver.quit();
 }
 

}
