package DHL_pack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;

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

public class TC022_uiputaway_nonlpn {

	
		
  @Test
  public void f() throws InterruptedException, IOException, AWTException {				  
		  
	
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
			dropdown1.selectByValue("LENOVO_SSEA");
			Thread.sleep(2000);
		     //SELECT CUSTOMER SERVICE TYPE
			Select dropdown2 = new Select(driver.findElement(By.id("customerServiceType")));
			dropdown2.selectByValue("Service Call");
			 //SELECT REQUESTER ID
			driver.findElement(By.id("requestorID")).sendKeys("len_ssea_edi_requestor", Keys.TAB);
			Thread.sleep(1000);
			// SELECT SERVICE LEVEL
			Select dropdown5 = new Select(driver.findElement(By.id("serviceLevel")));
			dropdown5.selectByValue("DEFERRED");
			Thread.sleep(1000);
			// SELECT CARRIER
			Select dropdown7 = new Select(driver.findElement(By.id("carrier")));
			dropdown7.selectByValue("20140425085906525424721");		
			Thread.sleep(1000);
			// SELECT ALLOCATION RULE
			Select dropdown4 = new Select(driver.findElement(By.id("allocationRuleId")));
			dropdown4.selectByValue("ND_SC_ALL");
			Thread.sleep(1000);
		    //SELECT SHIP FROM
			driver.findElement(By.id("shipfromWH")).click();
			driver.findElement(By.id("shipfromWH")).sendKeys("INICD05", Keys.TAB);
			  //SELECT CUSTOMER ORDER
			driver.findElement(By.id("ref1")).click();
			driver.findElement(By.id("ref1")).sendKeys(order,Keys.TAB);
		      //SELECT SAP ORDER NUMBER
			driver.findElement(By.id("ref4")).sendKeys(order,Keys.TAB);
			 //SELECT SHIP-TO-ID
			driver.findElement(By.id("shipToID")).sendKeys("LEN_WH_NEWDELHI",Keys.TAB);
			  //SELECT PART NUMBER
			 driver.findElement(By.id("partNumber_0")).click();
		    driver.findElement(By.id("partNumber_0")).sendKeys("16200208",Keys.TAB);
		     //SELECT PRODUCT CLASS
		    Select dropdown3 = new Select(driver.findElement(By.id("productClass_0")));
			dropdown3.selectByValue("GOOD");
			  //SELECT UOM
			Select dropdown6 = new Select(driver.findElement(By.id("uom_0")));
			dropdown6.selectByValue("EACH");
			  //SELECT QUANTITY
			driver.findElement(By.id("orderCaptureFB_orderLines_0__quantity")).sendKeys("1",Keys.TAB);
			driver.switchTo().defaultContent();
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
			driver.switchTo().frame("frame5");
			WebElement confirm_btn = driver.findElement(By.id("confirmBtn"));
				//CLICK CONFIRM
			Thread.sleep(2000);
			confirm_btn.click();			   
			   
		    //Assertion Check for Order Number ::
		    WebElement e2 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= '"+order+"']"))); 
			Assert.assertTrue(e2.isDisplayed());
		
			  driver.get("https://spl-test.dhl.com/portal/home/");
			  WebElement e5 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
		      e5.click();
			  driver.get("https://spl-test.dhl.com/smcfs/console/br1po.search");
			  WebElement dropDownListBox = driver.findElement(By.name("xml:/Order/@OrderNoQryType"));
			  Select clickThis = new Select(dropDownListBox);
			  clickThis.selectByVisibleText("is");
			  driver.findElement(By.name("xml:/Order/@OrderNo")).sendKeys(order,Keys.ENTER);
			  Thread.sleep(3000);
			  driver.findElement(By.name("btnSearch")).click();
			  WebElement e3 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='../yfcicons/moreactions.gif']")));
			  e3.click();
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
			   WebElement e4 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.name("xml:/Receipt/@ReceivingDock")));
			   e4.sendKeys("REC01");
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
			   WebElement frame2 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
			   driver.switchTo().frame(frame2);
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
			   WebElement e9 = driver.findElement(By.xpath("//*[text()='Received']"));
			   Assert.assertTrue(e9.isDisplayed());
				
			
		 	  driver.get("https://spl-test.dhl.com/portal/home/");
			  WebElement e6 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
		      e6.click();
			  driver.get("https://spl-test.dhl.com/smcfs/console/task.search");
			  // switch to search type :  By Reference
			  driver.get("https://spl-test.dhl.com/smcfs/console/task.search?SwitchToViewID=YWMS120");
			  WebElement dropDownListBox1 = driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNoQryType"));
			  Select drop = new Select(dropDownListBox1);
			  drop.selectByValue("EQ");
			  driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNo")).sendKeys(order, Keys.ENTER);
			  Thread.sleep(3000);
			  driver.findElement(By.name("btnSearch")).click();		  	   		
			  WebElement e7 = (new WebDriverWait(driver,30)).until(ExpectedConditions.presenceOfElementLocated(By.name("EntityKey")));
			  e7.click();
			  driver.findElement(By.xpath("//*[text()='�View Details']")).click();
			  driver.findElement(By.xpath("//*[text()='�Complete ']")).click();
			  Thread.sleep(3000);
			  String mainhandle2 = driver.getWindowHandle();
			  for(String winHandle :driver.getWindowHandles()){
		         driver.switchTo().window(winHandle);
			  }
			  		   
			  WebElement frame3 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
			  driver.switchTo().frame(frame3);
			  driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
			  driver.switchTo().window(mainhandle2);
			  driver.close();

	      
  }


}
