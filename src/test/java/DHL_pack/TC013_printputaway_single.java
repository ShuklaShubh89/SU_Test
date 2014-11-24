package DHL_pack;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import DHL_pack.UtilityMethods;

public class TC013_printputaway_single {
 
  @Test
  public void f() throws InterruptedException, IOException {
	
  	  String ordernum =  TC001_CreateOrder_Single.ordernum;
	  WebDriver driver = UtilityMethods.driverinitialize();
	  driver.get("https://spl-test.dhl.com/portal/home/");
	  UtilityMethods.login(driver);	
	  WebElement e1 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	  e1.click();
	  driver.get("https://spl-test.dhl.com/smcfs/console/task.search");
	  
	  // switch to search type :  By Reference
	  driver.get("https://spl-test.dhl.com/smcfs/console/task.search?SwitchToViewID=YWMS120");
	  WebElement dropDownListBox = driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNoQryType"));
	  Select drop = new Select(dropDownListBox);
	  drop.selectByValue("EQ");
	  driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNo")).sendKeys(ordernum, Keys.ENTER);
	  Thread.sleep(3000);
	  driver.findElement(By.name("btnSearch")).click();
	  		   	    
	  WebElement batchno = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'BatchNo:')]")));
	  batchno.click();
	  Thread.sleep(5000);
	  System.out.println(driver.getWindowHandles().size());
	  for(String winHandle :driver.getWindowHandles()){
              driver.switchTo().window(winHandle);
	   }
	  WebElement frame1  = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame"))); 	    		  
	  driver.switchTo().frame(frame1);

	  WebElement printbutton = driver.findElement(By.xpath("//*[contains(text(),'Putaway Sheet Print')]"));
	  Assert.assertTrue(printbutton.isEnabled());
	  printbutton.click();	  
	  
	  
  }
  
 
}
