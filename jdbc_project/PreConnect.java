package batch_file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreConnect {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public PreConnect() {
		try {
			String url = "jdbc:oracle:thin:@192.168.100.37:1521:orcl";
			String user = "B394121";
			String passwd = "B394121";
			
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("DB연결 성공");
			
			System.out.println("검색할 업무를 작성하세요");
			Scanner scanner = new Scanner(System.in);
			String code = scanner.nextLine();
			String name = "댓글";
			
//			stmt = con.createStatement();
//			stmt.executeQuery("select * from Example where c_no = "+ number +" and c_name = '"+ name +"'");
			
			pstmt = con.prepareStatement("select * from INTEGRATION_INFO where img_key like ?");
//			pstmt.setInt(1, number);
			pstmt.setString(1, "%"+code+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(3));
				
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.println("사유 : " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new PreConnect();
	}
	

}
