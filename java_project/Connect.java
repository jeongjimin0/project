


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Connect {

	public void Conn(String query) {
		 String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		 String DB_USER = "c##project_2_table_";
		 String DB_PASSWORD = "1234";
		 
		 
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 } catch (ClassNotFoundException e ) {
		 e.printStackTrace();
		 }
		 
		try {
		 conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		 
		          stmt = conn.createStatement();
		           rs = stmt.executeQuery(query);
		 
		          while (rs.next()) {
		                String empno = rs.getString(1);
		                String ename = rs.getString(2);
		                String job = rs.getString(3);
		                String mgr = rs.getString(4);
		                System.out.println(
		                     empno + " : " + ename + " : " + job + " : " + mgr);
		 }
		 } catch ( Exception e ) {
		 e.printStackTrace();
		 } finally {
		 try {
		 rs.close();
		 stmt.close();
		 conn.close();
		 } catch ( SQLException e ) {}
		 }

    }
		
	
	
	
	public static void main(String[] agrs) {
		Connect conn = new Connect();
		 Scanner scanner = new Scanner(System.in);
		 String query = scanner.nextLine();
		conn.Conn(query);
		
	}
	
}