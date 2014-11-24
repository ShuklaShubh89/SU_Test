package DHL_pack;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class TC003_CreateOrder_Multiple {


	public static String ordernum ;


	  @Test
	  public void f() throws InterruptedException, IOException {
	try{		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
		Date date1 = new Date();
		ordernum = dateFormat.format(date1);
		UtilityMethods.killdrivers();  
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
		Thread.sleep(1000);
	     //SELECT CUSTOMER SERVICE TYPE
		Select dropdown2 = new Select(driver.findElement(By.id("customerServiceType")));
		dropdown2.selectByValue("Inbound");
		  //SELECT REQUESTER ID
		driver.findElement(By.id("requestorID")).sendKeys("rio_global_edi_requestor", Keys.TAB);
		Thread.sleep(1000);
		  //SELECT SHIP TO
		driver.findElement(By.id("shiptoWH")).click();
		driver.findElement(By.id("shiptoWH")).sendKeys("MWRGWHS01", Keys.TAB);
		 //SELECT CUSTOMER ORDER
		driver.findElement(By.id("ref1")).click();
		driver.findElement(By.id("ref1")).sendKeys(ordernum,Keys.TAB);
	      //SELECT SAP ORDER NUMBER
		driver.findElement(By.id("ref4")).sendKeys(ordernum,Keys.TAB);
		 //SELECT COLLECTION SITE
		driver.findElement(By.id("collectionSiteId")).sendKeys("RIO_SITE_1",Keys.TAB);
		Thread.sleep(3000);
		int i=0,j=OrderNumVariables.totalitems -1  ;
		do
		{   //SELECT PART NUMBER
	    driver.findElement(By.id("partNumber_"+i)).click();
		driver.findElement(By.id("partNumber_"+i)).sendKeys("NORMAL-04",Keys.TAB);	
			//SELECT PRODUCT CLASS
		Select dropdown3 = new Select(driver.findElement(By.id("productClass_"+i)));
		dropdown3.selectByValue("GOOD");
			//SELECT UOM
		Select dropdown4 = new Select(driver.findElement(By.id("uom_"+i)));
		dropdown4.selectByValue("EACH");
		    //SELECT QUANTITY
		driver.findElement(By.id("orderCaptureFB_orderLines_"+i+"__quantity")).sendKeys("2");
		if(i!=j){
		driver.findElement(By.id("addOrderLine")).click();
		Thread.sleep(3000);
		}
		Thread.sleep(1000);
		i++;
		}while(i<=j);
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		driver.switchTo().frame("frame5");
		WebElement confirm_btn = driver.findElement(By.id("confirmBtn"));
			//CLICK CONFIRM
		Thread.sleep(2000);
		confirm_btn.click();					
		  //Assertion Check for Order Number ::
		//Assertion Check for Order Number ::
	    WebElement e2 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= '"+ordernum+"']")));  
		Assert.assertTrue(e2.isDisplayed());
		driver.quit();
	  }catch(org.openqa.selenium.TimeoutException e){
			 f();
		 }
	}
	
	  
	  
}
	
