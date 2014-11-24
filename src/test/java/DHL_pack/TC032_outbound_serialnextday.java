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

public class TC032_outbound_serialnextday {

	
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
		//SELECT PART NUMBER
		 driver.findElement(By.id("partNumber_0")).click();
	    driver.findElement(By.id("partNumber_0")).sendKeys("NORMAL-01",Keys.TAB);
	    //SELECT PRODUCT CLASS
	    Select dropdown8 = new Select(driver.findElement(By.id("productClass_0")));
		dropdown8.selectByValue("GOOD");
		//SELECT UOM
		Select dropdown6 = new Select(driver.findElement(By.id("uom_0")));
		dropdown6.selectByValue("EACH");
		//SELECT QUANTITY
		driver.findElement(By.id("orderCaptureFB_orderLines_0__quantity")).sendKeys("2",Keys.TAB);	
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		driver.switchTo().frame("frame5");
		WebElement confirm_btn = driver.findElement(By.id("confirmBtn"));
		//CLICK CONFIRM
		Thread.sleep(2000);
		confirm_btn.click();
		Thread.sleep(2000);
		confirm_btn.click();
		//Assertion Check for Order Number ::
	    WebElement e2 = (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= '"+order+"']"))); 
	   	System.out.println(e2.isDisplayed());
		Assert.assertTrue(e2.isDisplayed());
		WebElement dhlordernum =(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'110')]")));
		String order2 = dhlordernum.getText();
		OrderNumVariables.DHL_Ordernum[3] = order2;
		System.out.println("DHL_Ordernum[3] = "+order2);
		driver.quit();
  }   
	
  
}
