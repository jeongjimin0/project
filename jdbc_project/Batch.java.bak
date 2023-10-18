package batch_file;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Batch {

		private Connection con;
		private ResultSet rs;
		private PreparedStatement pstmt;
	
		public Batch() {
			
	        String resource = "C:\\Users\\정지민\\Desktop\\test\\File search\\src\\db.properties";
	        Properties properties = new Properties();
	        

	        try {
	        	
	            properties.load(new FileInputStream(resource));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
			try {
				
	            String user = properties.getProperty("user");
	            String passwd = properties.getProperty("passwd");
	            String url = properties.getProperty("url");
				
		        con = DriverManager.getConnection(url, user, passwd);
				System.out.println("DB연결 성공");
				
				System.out.println("이관할 업무 분류를 입력하세요");
				Scanner scanner = new Scanner(System.in);
				String code = scanner.nextLine();
				
				
				
//				stmt = con.createStatement();
//				stmt.executeQuery("select * from Example where c_no = "+ number +" and c_name = '"+ name +"'");
				
				pstmt = con.prepareStatement("INSERT INTO INTEGRATION_INFO(ELEMENTID, IMG_KEY, MAIN_CATEGORY, SEQ_NO, DOC_CD, FILE_NM, REGIST_USER_ID, UPDATE_USER_ID, REGIST_DATE, UPDATE_DATE) SELECT ELEMENTID, IMG_KEY, '공통', 1, DOC_CD, FILE_NM, ENR_ORG_CD, ENR_ORG_CD, ENR_DTM, ENR_DTM FROM XTORM.BIZ_INFO WHERE IMG_KEY LIKE ?");
//				pstmt.setInt(1, number);
				pstmt.setString(1, "%"+code+"%");
				
				int i = pstmt.executeUpdate();
				
				System.out.println(i + "개 삽입되었습니다.");
//				while (rs.next()) {
//					System.out.println(rs.getString(1) + "\t" + rs.getString(3));
//					
//				}
				
				pstmt.close();
				con.close();
				
			} catch (SQLException e) {
				System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
				System.out.println("사유 : " + e.getMessage());
			}
		}
		
		public static void main(String[] args) {
			new Batch();
		}
}
