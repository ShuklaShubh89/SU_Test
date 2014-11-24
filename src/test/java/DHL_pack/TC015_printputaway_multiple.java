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

public class TC015_printputaway_multiple {

	  @Test
	  public void f() throws InterruptedException, IOException {

		  String ordernum =  TC003_CreateOrder_Multiple.ordernum;
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
		  		  
		  	
		  WebElement e2 = (new WebDriverWait(driver,30)).until(ExpectedConditions.presenceOfElementLocated(By.name("EntityKey")));
		  e2.click();
		  driver.findElement(By.xpath("//*[text()='�View Details']")).click();
		  driver.findElement(By.xpath("//*[text()='�Complete ']")).click();
		  Thread.sleep(3000);
		  String mainhandle = driver.getWindowHandle();
		  for(String winHandle :driver.getWindowHandles()){
	          driver.switchTo().window(winHandle);
		  }	  		   
		   		  
		  WebElement frame =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
		  driver.switchTo().frame(frame);
		  driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
		  driver.switchTo().window(mainhandle);
		  		  
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//*[contains(text(),'BatchNo:')]")).click();
		  Thread.sleep(2000);
		  System.out.println(driver.getWindowHandles().size());
		  for(String winHandle :driver.getWindowHandles()){
	              driver.switchTo().window(winHandle);
		   }
		  WebElement frame2 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
		  driver.switchTo().frame(frame2);

		  WebElement printbutton = driver.findElement(By.xpath("//*[contains(text(),'Putaway Sheet Print')]"));
		  Assert.assertTrue(printbutton.isEnabled());
		  printbutton.click();
		 
	  }  
}
	  
