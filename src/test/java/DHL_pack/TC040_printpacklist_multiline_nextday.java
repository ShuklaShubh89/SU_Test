package DHL_pack;

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

public class TC040_printpacklist_multiline_nextday {
	
	
@Test
public void f() throws InterruptedException {		
	  
        String order = UtilityMethods.timestampedordernum();

	    WebDriver driver = UtilityMethods.driverinitialize();
	    driver.get("https://spl-test.dhl.com/portal/home/");
		UtilityMethods.login(driver);
		WebElement e1 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("wicket-generated-id-5")));
		e1.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame5");
		WebElement e3 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='jMenu']/li[4]/a")));
		e3.click();
		//SELECT CUSTOMER 
		Select dropdown1 = new Select(driver.findElement(By.id("customer")));
		dropdown1.selectByValue("RIO_GLOBAL");
		Thread.sleep(1000);
		//SELECT CUSTOMER SERVICE TYPE
		Select dropdown2 = new Select(driver.findElement(By.id("customerServiceType")));
		dropdown2.selectByValue("Outbound Transfer");
		// SELECT SERVICE LEVEL
		Select dropdown5 = new Select(driver.findElement(By.id("serviceLevel")));
		dropdown5.selectByValue("NEXT BUSINESS DAY");
		Thread.sleep(1000);	
		// SELECT CARRIER
		Select dropdown7 = new Select(driver.findElement(By.id("carrier")));
		dropdown7.selectByValue("20140425085906525424721");		
		Thread.sleep(1000);
		// SELECT ALLOCATION RULE
		Select dropdown4 = new Select(driver.findElement(By.id("allocationRuleId")));
		dropdown4.selectByValue("ND_SC_ALL");
		Thread.sleep(1000);
		//SELECT REQUESTER ID
		driver.findElement(By.id("requestorID")).sendKeys("rio_global_edi_requestor", Keys.TAB);
		Thread.sleep(1000);
		//SELECT SHIP FROM
		driver.findElement(By.id("shipfromWH")).click();
		driver.findElement(By.id("shipfromWH")).sendKeys("MWRGWHS01", Keys.TAB);
		//SELECT CUSTOMER ORDER
		driver.findElement(By.id("ref1")).click();
		driver.findElement(By.id("ref1")).sendKeys(order,Keys.TAB);
		//SELECT SAP ORDER NUMBER
		driver.findElement(By.id("ref4")).sendKeys(order,Keys.TAB);
		//SELECT COLLECTION SITE
		driver.findElement(By.id("shipToID")).sendKeys("RIO_SITE_1",Keys.TAB);
		int i=0,j=OrderNumVariables.totalitems -1  ;
		do
		{ //SELECT PART NUMBER
			WebElement e6 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("partNumber_"+i)));
			e6.click();
			e6.sendKeys("NORMAL-04",Keys.TAB);
			//SELECT PRODUCT CLASS
		Select dropdown3 = new Select(driver.findElement(By.id("productClass_"+i)));
		dropdown3.selectByValue("GOOD");
			//SELECT UOM
		Select dropdown6 = new Select(driver.findElement(By.id("uom_"+i)));
		dropdown6.selectByValue("EACH");
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
		Thread.sleep(2000);
		confirm_btn.click();
		WebElement dhlordernum =(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'110')]")));
		String order2 = dhlordernum.getText();
		System.out.println(order2);
		
		
	    	driver.get("https://spl-test.dhl.com/portal/home/");
	  	    WebElement e7 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	        e7.click();
			driver.get("https://spl-test.dhl.com/smcfs/console/BR2shipment.search");
			WebElement dropDownListBox = driver.findElement(By.name("xml:/Shipment/ShipmentLines/ShipmentLine/@OrderNoQryType"));
			Select clickThis = new Select(dropDownListBox);
			clickThis.selectByVisibleText("is");
			driver.findElement(By.name("xml:/Shipment/ShipmentLines/ShipmentLine/@OrderNo")).sendKeys(order2,Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.name("btnSearch")).click();
			WebElement e4 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("selectShipment_1")));
			e4.click();
			driver.findElement(By.xpath("//img[@src='../yfcicons/action.gif']")).click();	
			WebElement e5 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Complete Tasks')]")));
			e5.click();
			String mainhandle = driver.getWindowHandle();			    
			for(String winHandle :driver.getWindowHandles()){
		          driver.switchTo().window(winHandle);	              
			   }
			WebElement frame2 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
			driver.switchTo().frame(frame2);
			driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
			Thread.sleep(3000);
			driver.switchTo().window(mainhandle);
			WebElement e6 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Print Pack List')]")));
			Assert.assertTrue(e6.isDisplayed());
			e6.click();
			for(String winHandle :driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);	              
		   }			
			driver.close();
  }   
}
