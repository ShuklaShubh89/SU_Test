package DHL_pack;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UtilityMethods {

	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    
    
// Methods to return current screen resolution
public static int resolutioncheck_width(){
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	return width;
}
public static int resolutioncheck_height(){   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int)screenSize.getHeight();
	return height;
}


// Kills the unnecessary processes of IEDriverServer      
public static void killdrivers() throws IOException, InterruptedException{
	Runtime rt = Runtime.getRuntime();
	rt.exec("taskkill /F /IM IEDriverServer.exe /T");
	Thread.sleep(4000);
}
      
// This method is for the time stamped order number generator
public static String timestampedordernum(){
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
    Date date = new Date();
    String ordernum = dateFormat.format(date);
    return ordernum;
}

// This Method is to read order numbers for EDI message check from the excel file
public static String ediordernums(int index) throws Exception{
	setExcelFile("C:/Users/shubham.a.shukla/workspace_selenium/DHL_SU/Edi Check Order Numbers.xlsx");
	String a[] = new String[getRowCount()];
	for(int i=1;i<getRowCount();i++)
	{
	 a[i] = getCellData(i, 2);
	}
	return a[index];
}
//This method is for the time stamped case id/lpn number generator
public static String timestampedcaseid(){
    DateFormat dateFormat = new SimpleDateFormat("hhmmss");
    Date date = new Date();
    String caseid = dateFormat.format(date);
    return caseid;
}

// This method is for login of the application      
public static void login(WebDriver driver)
{
		driver.findElement(By.id("username")).sendKeys("ext_gshukla");
		driver.findElement(By.id("password")).sendKeys("Accenture5",Keys.ENTER);
		//driver.findElement(By.id("OKButton")).click();
}


// This method is for the web driver initialization
public static WebDriver driverinitialize()
{
	File file1 = new File("C:\\selenium-2.42.2\\IEDriverServer.exe");
	System.setProperty("webdriver.ie.driver", file1.getAbsolutePath());
	DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
	dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
	dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	WebDriver driver = new InternetExplorerDriver(dc);
	return driver;
}
      


//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
public static void setExcelFile(String Path)  {
         try {
             // Open the Excel file
          FileInputStream ExcelFile = new FileInputStream(Path);
          // Access the required test data sheet
          ExcelWBook = new XSSFWorkbook(ExcelFile);
          } catch (Exception e){
             e.printStackTrace();
          }

}
  

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
public static String getCellData(int RowNum, int ColNum) throws Exception{
         try{
        	ExcelWSheet = ExcelWBook.getSheetAt(0);   	  
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
            }catch (Exception e){
              return "error in returning cell data";
            }

  }


//This method is to get the row count used of the excel sheet
public static int getRowCount(){
        ExcelWSheet = ExcelWBook.getSheetAt(0);  
        int number=ExcelWSheet.getLastRowNum()+1;
        return number;
    }
  

//This method is to write the test data to the Excel cell,
public static void putCellData(int RowNum, int ColNum, String Value) throws Exception{
    try{
       ExcelWSheet = ExcelWBook.getSheetAt(0);   	  
       Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
       Cell.setCellValue(Value);
       FileOutputStream out = new FileOutputStream(new File("C:/Users/shubham.a.shukla/workspace_selenium/DHL_SU/src/TestData.xlsx"));     
       ExcelWBook.write(out);
       out.flush();
	   out.close();
       }catch (Exception e){
        e.printStackTrace();
       }

    }


// This method is to create a Database connection to the server.
public static Connection DBConnection() throws ClassNotFoundException, SQLException{
	
	System.out.println("Connecting DB...");
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String serverName = "23.252.145.141";
    String portNumber = "1521";
    String sid = "SUTEST";
    String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + "/" + sid;
    String username = "strreadonly";
    String password = "strreadonly321";
    Connection conn = DriverManager.getConnection(url, username, password);
    System.out.println("Database Connected Successfully");      
    return conn;
    
} 

public void mail(Object[] args) 
	{
		try{
       // Recipient's email ID needs to be mentioned.
       String to = "sarvesh.kalia@accenture.com";
    

       // Sender's email ID needs to be mentioned
       String from = "shubham.a.shukla@accenture.com";

      // Host address
       String host = "email.o365.accenture.com";

       // Get system properties
       Properties properties = System.getProperties();

       // Setup mail server
       // Get the default Session object.
  /*     properties.setProperty("mail.user", "myuser");
       properties.setProperty("mail.password", "mypwd");*/
       properties.setProperty("mail.smtp.port", "25");
       properties.setProperty("mail.smtp.host", host);
       properties.put("mail.debug", "true");
       Session session = Session.getDefaultInstance(properties);

      // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(from));

       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO,
                                   new InternetAddress(to));

       // Set Subject: header field
       message.setSubject("SAP HealthChecks");
       BodyPart messageBodyPart = new MimeBodyPart();
       Multipart multipart = new MimeMultipart();
    // Now set the actual message
       message.setText("Please find the SAP Health checks xlsx attached");
       message.saveChanges();
       // Set text message part
       // multipart.addBodyPart(messageBodyPart);
       messageBodyPart = new MimeBodyPart();
       String filename = "C:\\Users\\shubham.a.shukla\\IBM\\rationalsdp\\workspace\\FI_HealthCheck\\Health_Checks_SAP.xlsx";
   /*  ((MimeBodyPart) messageBodyPart).attachFile(new File(filename));
       messageBodyPart.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"; name=\"mail.txt");*/
       DataSource source = new FileDataSource(filename);
       messageBodyPart.setDataHandler(new DataHandler(source));
       messageBodyPart.setFileName(filename);
       multipart.addBodyPart(messageBodyPart);
       message.setContent(multipart);
       message.saveChanges();
                    
       
       // Send message
       Transport.send(message);
       System.out.println("Sent message successfully....");
		}catch(Exception e){
		   e.printStackTrace();
		}
	}

}