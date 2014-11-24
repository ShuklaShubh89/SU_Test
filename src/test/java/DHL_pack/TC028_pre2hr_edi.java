package DHL_pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC028_pre2hr_edi {
	 @Test
	  public void f() throws Exception {
		  
		  
			Connection c =	UtilityMethods.DBConnection();
			Statement stmt = c.createStatement();
			String ordernum = UtilityMethods.ediordernums(3);
			String timesign = ordernum.substring(0, 8);
		    String sql = "select message from strprod.yfs_export where export_key>'"+timesign+"0500' and export_key<'"+timesign+"2300' and message like '%"+ordernum+"%'";
		    ResultSet rs = stmt.executeQuery(sql);
		        
		    while(rs.next()){	    	
		    //Retrieve by column name
		    String message  = rs.getString("message");	        
		    //Display values
		    System.out.println(message);	    
		    Assert.assertEquals(true, message.contains(ordernum));
		    
		    }
	   
	  }
}
