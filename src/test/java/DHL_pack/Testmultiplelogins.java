package DHL_pack;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Testmultiplelogins

{
	
public static void main(String argv[]) throws Exception{

	File file1 = new File("C:\\selenium-2.42.2\\IEDriverServer.exe");
	System.setProperty("webdriver.ie.driver", file1.getAbsolutePath());
	DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
	dc.setCapability("native events", false);
	WebDriver driver = new InternetExplorerDriver(dc);
	int i = 0;
	
	String username = null ,password  =null;
	UtilityMethods.setExcelFile("C:/Users/shubham.a.shukla/workspace_selenium/DHL_SU/src/TestData.xlsx");
	
	for(i=1;i<UtilityMethods.getRowCount();i++)
	{
	 try{
	driver.get("https://spl-test.dhl.com/portal/home/");
	if(UtilityMethods.getCellData(i, 3).equalsIgnoreCase("Y"))
	{
	WebElement username_box = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	username_box.click();
	username = UtilityMethods.getCellData(i, 0);		
	password = UtilityMethods.getCellData(i, 1);			
	driver.findElement(By.id("username")).sendKeys(username);
	driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("OKButton")).click();
    WebElement logout =   (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("logoutMenu")));
    UtilityMethods.putCellData(i, 2, "Pass");
    logout.click();  
		}
		}catch(Exception e)
		{
		   UtilityMethods.putCellData(i, 2, "Fail");
		}
		}
	}
	  
}
    

