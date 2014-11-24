package DHL_pack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC009_uireceiving_wmsinbound_single {
	   

	@Test
	public void f() throws IOException, InterruptedException, AWTException {
	
		  String ordernum = TC001_CreateOrder_Single.ordernum;
		  try{
		  UtilityMethods.killdrivers();
 		  WebDriver driver = UtilityMethods.driverinitialize();
		  driver.get("https://spl-test.dhl.com/portal/home/");
		  UtilityMethods.login(driver);	
		  WebElement e1 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	      e1.click();
		  driver.get("https://spl-test.dhl.com/smcfs/console/br1po.search");
		  WebElement dropDownListBox = driver.findElement(By.name("xml:/Order/@OrderNoQryType"));
		  Select clickThis = new Select(dropDownListBox);
		  clickThis.selectByVisibleText("is");
		  driver.findElement(By.name("xml:/Order/@OrderNo")).sendKeys(ordernum,Keys.ENTER);
		  Thread.sleep(3000);
		  driver.findElement(By.name("btnSearch")).click();
		  WebElement e2 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='../yfcicons/moreactions.gif']")));
		  e2.click();
		  Thread.sleep(1000);
				
		  Robot robot = new Robot();
		  robot.mouseMove(OrderNumVariables.coordinate_x,OrderNumVariables.coordinate_y);    
		  robot.mousePress(InputEvent.BUTTON1_MASK);
		  robot.mouseRelease(InputEvent.BUTTON1_MASK);
		  Thread.sleep(6000);
		  System.out.println(driver.getWindowHandles().size());
	      String mainhandle = driver.getWindowHandle();		    
	 	  for(String winHandle :driver.getWindowHandles()){
	              driver.switchTo().window(winHandle);	              
			   }		        
	     for(String winHandle :driver.getWindowHandles()){
               driver.switchTo().window(winHandle);               
		   }		 	 
		 for(String winHandle :driver.getWindowHandles()){
	             driver.switchTo().window(winHandle);
		   }
		  
		   WebElement frame1  = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame"))); 	    		  
		   driver.switchTo().frame(frame1);
		   WebElement e3 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.name("xml:/Receipt/@ReceivingDock")));
		   e3.sendKeys("REC01");
		   driver.findElement(By.name("xml:/Receipt/Extn/@ExtnTruckArrivalTime_Date")).sendKeys("10/13/2014");
		   driver.findElement(By.name("xml:/Receipt/Extn/@ExtnTruckArrivalTime_Time")).sendKeys("11:30:30");
		   driver.findElement(By.name("xml:/Receipt/Extn/@ExtnAirwaybillNo")).sendKeys("12354523");
		   WebElement carrierdrop =  driver.findElement(By.name("xml:/Receipt/Extn/@ExtnCarrierService"));
		   Select carr = new Select(carrierdrop);
		   carr.selectByValue("CANADA UPS STANDARD");
		   driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
		   Thread.sleep(5000);
		   driver.switchTo().defaultContent();	
	   
		   System.out.println(driver.getWindowHandles().size());
		   for(String winHandle :driver.getWindowHandles()){
	              driver.switchTo().window(winHandle);	              
			}			    			    		    
			WebElement frame =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
			driver.switchTo().frame(frame);
			List<WebElement> elements = driver.findElements(By.name("xml:/Receipt/@JunkId"));
			elements.get(1).click();
			String caseid = UtilityMethods.timestampedcaseid();
			driver.findElement(By.name("xml:/Receipt/@CaseId")).sendKeys(caseid);
			WebElement dcdrop = driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_1/@DispositionCode"));	    
			Select dc = new Select(dcdrop);
			dc.selectByValue("GOOD");
			driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_1/@Quantity")).clear();
			driver.findElement(By.name("xml:/Receipt/ReceiptLines/ReceiptLine_1/@Quantity")).sendKeys("2");
			driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
			Thread.sleep(2000);
			driver.close();
			driver.switchTo().window(mainhandle);
		    
			WebElement e4 = driver.findElement(By.xpath("//*[text()='Received']"));
			Assert.assertTrue(e4.isDisplayed());
			driver.quit();
		}catch(org.openqa.selenium.TimeoutException e){
			 f();
		 }
	} 
	
}
