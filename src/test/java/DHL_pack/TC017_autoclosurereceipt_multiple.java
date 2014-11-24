package DHL_pack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC017_autoclosurereceipt_multiple {

	 
	  @Test
	  public void f() throws InterruptedException, IOException, AWTException {
	

		String order = UtilityMethods.timestampedordernum();
	  
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
		driver.findElement(By.id("ref1")).sendKeys(order,Keys.TAB);
	      //SELECT SAP ORDER NUMBER
		driver.findElement(By.id("ref4")).sendKeys(order,Keys.TAB);
		 //SELECT COLLECTION SITE
		driver.findElement(By.id("collectionSiteId")).sendKeys("RIO_SITE_1",Keys.TAB);
		Thread.sleep(3000);
		int i=0,j=OrderNumVariables.totalitems -1  ;
		do
		{   //SELECT PART NUMBER
			
		WebElement e6 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("partNumber_"+i)));
		e6.click();
		e6.sendKeys("NORMAL-04",Keys.TAB);	
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
	    WebElement e2 = (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+order+"')]")));
		Assert.assertTrue(e2.isDisplayed());
	  
	
	  driver.get("https://spl-test.dhl.com/portal/home/");
	  WebElement e4 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	  e4.click();
	  driver.get("https://spl-test.dhl.com/smcfs/console/br1po.search");
	  WebElement dropDownListBox = driver.findElement(By.name("xml:/Order/@OrderNoQryType"));
	  Select clickThis = new Select(dropDownListBox);
	  clickThis.selectByVisibleText("is");
	  driver.findElement(By.name("xml:/Order/@OrderNo")).sendKeys(order,Keys.ENTER);
	  Thread.sleep(3000);
	  driver.findElement(By.name("btnSearch")).click();
	  Thread.sleep(3000);
      WebElement e6 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='../yfcicons/moreactions.gif']")));
	  e6.click();
	  Thread.sleep(1000);
		
	  Robot robot = new Robot();
	  robot.mouseMove(OrderNumVariables.coordinate_x,OrderNumVariables.coordinate_y);    
	  robot.mousePress(InputEvent.BUTTON1_MASK);
	  robot.mouseRelease(InputEvent.BUTTON1_MASK);
      Thread.sleep(7000);

	  String mainhandle = driver.getWindowHandle();
	    
	  for(String winHandle :driver.getWindowHandles()){
             driver.switchTo().window(winHandle);	              
		   }	        
	  for(String winHandle :driver.getWindowHandles()){
          driver.switchTo().window(winHandle);               
	   }	 
	  System.out.println(driver.getWindowHandles().size());
	    for(String winHandle :driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
		   }
	   
	  System.out.println(driver.getWindowHandles().size());
	    
	  WebElement frame1  = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame"))); 	    		   		  
	  driver.switchTo().frame(frame1);
	  driver.findElement(By.name("xml:/Receipt/@ReceivingDock")).sendKeys("REC01");
	  driver.findElement(By.name("xml:/Receipt/Extn/@ExtnTruckArrivalTime_Date")).sendKeys("10/13/2014");
	  driver.findElement(By.name("xml:/Receipt/Extn/@ExtnTruckArrivalTime_Time")).sendKeys("11:30:30");
	  driver.findElement(By.name("xml:/Receipt/Extn/@ExtnAirwaybillNo")).sendKeys("12354523");
	  WebElement carrierdrop =  driver.findElement(By.name("xml:/Receipt/Extn/@ExtnCarrierService"));
	  Select carr = new Select(carrierdrop);
	  carr.selectByValue("CANADA UPS STANDARD");
	  driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
	  Thread.sleep(5000);
	  driver.switchTo().window(mainhandle);
	    
	   for(String winHandle :driver.getWindowHandles()){
           driver.switchTo().window(winHandle);
		   }
	    for(String winHandle :driver.getWindowHandles()){
         driver.switchTo().window(winHandle);
		   }
	
	    WebElement frame2  = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame"))); 	    		   		  
		driver.switchTo().frame(frame2);
		List<WebElement> elements = driver.findElements(By.name("xml:/Receipt/@JunkId"));
		elements.get(1).click();
		elements.get(1).click();
		String caseid = UtilityMethods.timestampedcaseid();
		driver.findElement(By.name("xml:/Receipt/@CaseId")).sendKeys(caseid);
		for(int l=1;l<=OrderNumVariables.totalitems;l++){
			WebElement dcdrop = driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_"+l+"/@DispositionCode"));	    
			Select dc = new Select(dcdrop);
			dc.selectByValue("GOOD");
			driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_"+l+"/@Quantity")).clear();
			driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_"+l+"/@Quantity")).sendKeys("2");
			}
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(mainhandle);
	  
	
	 for (long stop=System.nanoTime()+TimeUnit.SECONDS.toNanos(130);stop>System.nanoTime();) {
	 driver.findElement(By.xpath("//*[@alt ='Refresh']")).click();
     Thread.sleep(3000);
	 if(isAlertPresent(driver))
	  {
	  Thread.sleep(2000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(2000);	 
	  }
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//*[text()='Received']")).isDisplayed()) 
			continue;
		else
			 break;
		}
	  //Assert for Receipt Closed Status
	  Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Receipt Closed']")).isDisplayed());
	  driver.quit();
	  
 }
 
 
 public static boolean isAlertPresent(WebDriver drive) throws InterruptedException 
	{ 
	    try 
	    { 
	    	Thread.sleep(2000);
	        drive.switchTo().alert(); 
	        System.out.println(drive.getTitle());
	        Thread.sleep(2000);
	        return true; 
	    }   
	    catch (UnhandledAlertException Ex) 
	    { 
	        return false; 
	    }  
	}  

}
