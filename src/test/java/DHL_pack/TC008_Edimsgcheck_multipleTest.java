package DHL_pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TC008_Edimsgcheck_multipleTest {
  @Test
  public void f() throws ClassNotFoundException, SQLException {
	  
	  
		Connection c =	UtilityMethods.DBConnection();
		Statement stmt = c.createStatement();
	   	String order = TC003_CreateOrder_Multiple.ordernum;
		String timesign = order.substring(0, 8);
	    String sql = "select message from strprod.yfs_export where export_key>'"+timesign+"0500' and export_key<'"+timesign+"2300' and message like '%"+order+"%'";
	    ResultSet rs = stmt.executeQuery(sql);
	        
	    while(rs.next()){
	    //Retrieve by column name
	    String message  = rs.getString("message");      
	    //Display values
	    System.out.println(message); 
	    Assert.assertEquals(true, message.contains(order));
	    
	    }
	   
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
