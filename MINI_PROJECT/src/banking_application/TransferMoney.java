package banking_application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TransferMoney {
	private static final int NULL = 0;
	static String sql = "";
	public static boolean transferMoney(int sender_ac,int reveiver_ac,int amount) throws SQLException 
    {
		if (reveiver_ac == NULL || amount == NULL) {
            System.out.println("All Field Required!");
            return false;
        }
        try {
        	Connection con = connection.getConnection();
            con.setAutoCommit(false);
            sql = "select * from user where ac_no="+ sender_ac;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {            	
                if (rs.getInt("balance") < amount) {
                    System.out.println("Insufficient Balance!");
                    return false;
                }
            }
            Statement st = con.createStatement();
            con.setSavepoint();
            sql = "update user set balance=balance-"+ amount + " where ac_no=" + sender_ac;
            if (st.executeUpdate(sql) == 4) {
                System.out.println("Amount Debited!");
            }
            sql = "update user set balance=balance+" + amount + " where ac_no= " + reveiver_ac;
            st.executeUpdate(sql); 
            con.commit();
            return true;
        }
        catch (Exception e) {
        	Connection con = connection.getConnection();
            e.printStackTrace();
            con.rollback();
        }
        return false;
    }
}
