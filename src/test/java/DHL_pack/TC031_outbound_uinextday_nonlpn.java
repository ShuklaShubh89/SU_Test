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

public class TC031_outbound_uinextday_nonlpn {

	
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
			dropdown1.selectByValue("LENOVO_SSEA");
			Thread.sleep(3000);
		    //SELECT CUSTOMER SERVICE TYPE
			WebElement e4 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("customerServiceType")));
			Select dropdown2 = new Select(e4);
			dropdown2.selectByValue("Service Call");
			//SELECT REQUESTER ID
			driver.findElement(By.id("requestorID")).sendKeys("len_ssea_edi_requestor", Keys.TAB);
			Thread.sleep(1000);
			// SELECT SERVICE LEVEL
			WebElement e5 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("serviceLevel")));
			Select dropdown5 = new Select(e5);
			dropdown5.selectByValue("SCHEDULED");
			Thread.sleep(2000);
			// SELECT CARRIER
			Select dropdown7 = new Select(driver.findElement(By.id("carrier")));
			dropdown7.selectByValue("20140425085906525424721");		
			Thread.sleep(2000);
			// SELECT ALLOCATION RULE
			Select dropdown4 = new Select(driver.findElement(By.id("allocationRuleId")));
			dropdown4.selectByValue("SC_ALL");
			Thread.sleep(2000);
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
		    WebElement e2 = (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= '"+order+"']"))); 
		   	System.out.println(e2.isDisplayed());
			Assert.assertTrue(e2.isDisplayed());
	        driver.quit();
       }
  

}
