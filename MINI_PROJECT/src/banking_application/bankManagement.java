package banking_application;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
public class bankManagement {  
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND= "\u001B[41m";
    public static final String CYAN_BACKGROUND="\u001B[46m";
    public static final String BLACK="\u001B[30m";
    private static final int NULL = 0; 
    static Connection con = connection.getConnection();
    static String sql = "";
	private static int ac_no;
	private static int balance;
    public static boolean
    createAccount(String userName,int password)
    {
        try {
            if (userName == "" || password == NULL) {
                System.out.println("All Field Required!");
                return false;
            }
            Statement st = con.createStatement();
            sql = "INSERT INTO user(user_name,balance,password) values('"+ userName + "',1000," + password + ")";
            if (st.executeUpdate(sql) == 1) {
                return true;
            }
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Try another Password....This password Alredy exists...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean
    loginAccount(String user_name, int password)
    {
        try {
            if (user_name == "" || password == NULL) {
                System.out.println("All Field Required!");
                return false;
            }
            sql = "select * from user where user_name='"+ user_name + "' and password=" + password;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in)); 
            if (rs.next()) {
            	int ch = 6;
                int amt = 0;
                int senderAc = rs.getInt("ac_no");
                int receiveAc;
                while (true) {
                    try {
                        System.out.println("             !!!!!Login Successfully!!!!!");
                        System.out.println("\n------------------------------------------------------");
                        System.out.println(ANSI_RED_BACKGROUND+"         Welcome to our IndianBank,   "+ rs.getString("user_name")+"           "+ANSI_RESET);
                        System.out.println("\n                   1)Deposit Money");
                        System.out.println("                   2)Withdraw Money");
                        System.out.println("                   3)Transaction Money");
                        System.out.println("                   4)View Balance");
                        System.out.println("                   5)LogOut");
                        System.out.println("\n------------------------------------------------------");
                        System.out.print(CYAN_BACKGROUND+BLACK+"Enter Choice  :  ");
                        ch = Integer.parseInt(sc.readLine());
                        System.out.println(ANSI_RESET);
                        if (ch == 1) {
                            System.out.print("                 Enter Amount : ");
                            amt = Integer.parseInt(sc.readLine());
                            System.out.println("\n------------------------------------------------------");
                            if (Deposit.depositMoney(ac_no, amt)) {
                            System.out.println("\n       !!!!!Amount Deposited Successfully!!!!!\n");
                            }
                        }
                        if (ch == 2) {
                            System.out.print("               Enter Amount : ");
                            amt = Integer.parseInt(sc.readLine());
                            System.out.println("\n------------------------------------------------------");
                            if (Withdraw.withdrawMoney(ac_no, amt)) {
                            System.out.println("\n       !!!!!Amount Withdrawn Successfully!!!!!\n");
                            }
                        }
                        if (ch == 3) {
                            System.out.print("             Enter Receiver  A/c No : ");
                            receiveAc = Integer.parseInt(sc.readLine());
                            System.out.print("             Enter Amount           : ");
                            amt = Integer.parseInt(sc.readLine());
                            System.out.println("\n------------------------------------------------------");
                            if (TransferMoney.transferMoney(senderAc, receiveAc, amt)) {
                                System.out.println("\n                 Money Sent Successfully!\n");
                            }
                            else {
                                System.out.println(
                                    "Failed!\n");
                            }
                        }
                        else if (ch == 4) {                        	
                        	System.out.print("              Enter Account Number : ");
                            ac_no = Integer.parseInt(sc.readLine());
                        	if(BalanceCheck.checkMoney(ac_no)) {
                        	}
                        }
                        else if (ch == 5) {
                            break;
                        }
                        
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                return false;
            }
            return true;
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username Not Available!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    
}