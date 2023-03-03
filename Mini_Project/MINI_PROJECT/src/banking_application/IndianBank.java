package banking_application; 
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
public class IndianBank {
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND= "\u001B[41m";
    public static final String YELLOW_BACKGROUND="\u001B[43m";
    public static final String BLACK="\u001B[30m";
    public static void main(String args[]) throws IOException
    {    	
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String userName;
        int password;
        int ch;
        while (true) {
            System.out.println(ANSI_RED_BACKGROUND+ "\n------------- Welcome to our Indian_Bank -------------\n"   + ANSI_RESET);
            System.out.println("                   1)Create Account"        );
            System.out.println("                   2)Login Account"         );
            try {
            	System.out.println("\n------------------------------------------------------");
                System.out.print(YELLOW_BACKGROUND+BLACK+"Enter Input  :  " ); 
                ch = Integer.parseInt(sc.readLine());
                System.out.println(ANSI_RESET);
                switch (ch) {
                case 1:
                    try {
                    	
                        System.out.print("            Enter UserName      :  ");
                        userName = sc.readLine();
                        System.out.print("            Enter New Password  :  ");
                        password = Integer.parseInt(sc.readLine());
                        System.out.println("\n------------------------------------------------------");
                        if (bankManagement.createAccount(userName, password)) {
                            System.out.println("\n       ++++++Account Created Successfully++++++\n");
                        }
                        else {
                            System.out.println("            Account Creation Failed!\n");
                        }
                    }
                    catch (Exception e) {
                        System.out.println(" Enter Valid Data::Insertion Failed!\n");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("               Enter  UserName : ");
                        userName = sc.readLine();
                        System.out.print("               Enter  Password : ");
                        password = Integer.parseInt(sc.readLine());
                        System.out.println("\n------------------------------------------------------");
                        if (bankManagement.loginAccount(userName, password)) {
                            System.out.println("                Logout Successfully!\n");
                            System.out.println("       Thank you for using our banking services");
                        }
                        else {
                            System.out.println("login Failed!\n");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Enter Valid Data::Login Failed!\n");
                        System.out.println("\n------------------------------------------------------");
                    }
                    break;                     
                default:
                    System.out.println("Invalid Entry!\n");
                }
                if (ch == 5) {
                    System.out.println("Exited Successfully!\n\n Thank You :)");
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Enter Valid Entry!");
            }
        }
        sc.close();
    }
}