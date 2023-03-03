package banking_application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Deposit {
	static String sql = "";
    static int balance=0;
	private static final int NULL = 0;
	public static boolean depositMoney(int ac_no, int amt) {
		if ( amt == NULL) {
            System.out.println("All Field Required!");
            return false;
        }
		try {
			Connection con = connection.getConnection();
	        con.setAutoCommit(false);
	        sql = "select * from user where ac_no="+ ac_no;
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	if (rs.getInt("balance") < 0) {
	        		System.out.println("Insufficient Balance!");
	                    return false;
	                }
	            }
	        Statement st = con.createStatement();
            con.setSavepoint();
            sql = "update user set balance=balance+"+ amt + " where ac_no=" + ac_no;
            
            if (st.executeUpdate(sql) == 1) {
            	
            }
            con.commit();
            return true;            
       }
       catch (Exception e) {
    	   e.printStackTrace();          
       }
       return false;
    }
}
