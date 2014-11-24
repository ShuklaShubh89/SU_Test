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

public class TC014_printincorrectorderid {
	
	
	
	@Test
	  public void f() throws InterruptedException, IOException {
	    
		  WebDriver driver = UtilityMethods.driverinitialize();
		  driver.get("https://spl-test.dhl.com/portal/home/");
		  UtilityMethods.login(driver);	
		  WebElement e1 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	      e1.click();
		  driver.get("https://spl-test.dhl.com/smcfs/console/task.search");
		  // switch to search type :  By Reference
		  driver.get("https://spl-test.dhl.com/smcfs/console/task.search?SwitchToViewID=YWMS120");
		  WebElement dropDownListBox = driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNoQryType"));
		  Select drop = new Select(dropDownListBox);
		  drop.selectByValue("EQ");
		  driver.findElement(By.name("xml:/Task/TaskReferences/@OrderNo")).sendKeys("g324rffd", Keys.ENTER);
		  Thread.sleep(3000);
		  driver.findElement(By.name("btnSearch")).click();
		  WebElement searchresult = (new WebDriverWait(driver,30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='0']")));
		  Assert.assertTrue(searchresult.isDisplayed());
		  driver.quit();
	  }
}	  
