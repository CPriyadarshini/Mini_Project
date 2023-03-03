package banking_application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class BalanceCheck {
	static String sql = "";
	public static boolean checkMoney(int ac_no)  {
        try {        	
        	Connection con = connection.getConnection();
            sql = "select * from user where ac_no="+ ac_no;
            PreparedStatement st= con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.printf("|%12s | |%9s    | |%10s  | \n","Account No", "Name","Balance");
            System.out.println("----------------------------------------------");
            while (rs.next()) {
            	System.out.printf("|%9d    | |%10s   | |%8d    | \n",rs.getInt("ac_no"),rs.getString("user_name"),rs.getInt("balance"));
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }
}
