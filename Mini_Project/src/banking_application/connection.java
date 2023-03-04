package banking_application;
import java.sql.Connection;
import java.sql.DriverManager;
public class connection {
	public static Connection con;
    public static Connection getConnection()
    {
       try {
    	   Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/indian_bank", "root","Priya@1999");
       }
       catch (Exception e) {
           System.out.println(e);
       }
       return con;
   }
}
