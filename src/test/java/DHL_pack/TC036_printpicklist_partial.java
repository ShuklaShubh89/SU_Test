package DHL_pack;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC036_printpicklist_partial {
	
	
@Test
public void f() throws InterruptedException {

	 WebDriver driver = UtilityMethods.driverinitialize();
	 driver.get("https://spl-test.dhl.com/portal/home/");
	 UtilityMethods.login(driver);	
	 WebElement e1 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
	 e1.click();
	 driver.get("https://spl-test.dhl.com/smcfs/console/BR2shipment.search");
	 WebElement dropDownListBox = driver.findElement(By.name("xml:/Shipment/ShipmentLines/ShipmentLine/@OrderNoQryType"));
	 Select clickThis = new Select(dropDownListBox);
	 clickThis.selectByVisibleText("is");
	 driver.findElement(By.name("xml:/Shipment/ShipmentLines/ShipmentLine/@OrderNo")).sendKeys(OrderNumVariables.DHL_Ordernum[1],Keys.ENTER);
	 Thread.sleep(3000);
	 driver.findElement(By.name("btnSearch")).click();
	 WebElement e6 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'WMS']")));
     e6.click();
	 WebElement e2 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("selectShipment_1")));
	 e2.click();
	 WebElement e3 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'View Details')]")));
	 e3.click();  
	 WebElement e4 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Complete Tasks')]")));
	 e4.click();
	 String mainhandle = driver.getWindowHandle();			    
	 for(String winHandle :driver.getWindowHandles()){
             driver.switchTo().window(winHandle);	              
	   }
	  WebElement frame2 =  (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("yfcRootFrame")));	  
	  driver.switchTo().frame(frame2);
	  driver.findElement(By.name("xml:/Task/Inventory/@Quantity")).clear();
	  driver.findElement(By.name("xml:/Task/Inventory/@Quantity")).sendKeys("1");
	  driver.findElement(By.xpath("//INPUT[@value = 'Save']")).click();
	  Thread.sleep(3000);
	  driver.switchTo().window(mainhandle);
	  WebElement e5 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Print Pick List')]")));
	  e5.click();
	  for(String winHandle :driver.getWindowHandles()){
          driver.switchTo().window(winHandle);	              
   }
	  driver.close();

  }

}
