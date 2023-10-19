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
	        String resource2 = "C:\\Users\\정지민\\Desktop\\test\\File search\\src\\batch_file\\query.xml";
	        Properties properties = new Properties();
	        Properties properties2 = new Properties();
	        int end = 0;
	        Scanner scanner = new Scanner(System.in);
	        int menu = 0;

	        try {
	            properties.load(new FileInputStream(resource));
	            properties2.loadFromXML(new FileInputStream(resource2));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	        while (end != -1) {
			try {
				
	            String user = properties.getProperty("user");
	            String passwd = properties.getProperty("passwd");
	            String url = properties.getProperty("url");
	            String sql = properties2.getProperty("selectinsert");
	            String sqld = properties2.getProperty("delete");
				
		        con = DriverManager.getConnection(url, user, passwd);
				System.out.println("DB연결 성공");
				System.out.println("0: 이관 \t 1: 삭제 \t 3: 뒤로가기");
				Scanner scanner3 = new Scanner(System.in);
				menu = scanner3.nextInt();
				
				switch (menu) {
				
				case 0:
				System.out.println("이관할 업무 분류를 입력하세요");
				Scanner scanner2 = new Scanner(System.in);
				String code = scanner2.nextLine();
				
				pstmt = con.prepareStatement(sql);
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
				break;

				case 1:
				System.out.println("삭제할 업무를 입력하세요");
				Scanner scanner4 = new Scanner(System.in);
				String delete = scanner4.nextLine();
				
				pstmt = con.prepareStatement(sqld);
				pstmt.setString(1, "%"+delete+"%");
				int d = pstmt.executeUpdate();
				
				System.out.println( d + "개 삭제되었습니다.");
				break;
				
				case 3:
					break;
				
				default:
					System.out.println("잘못된 메뉴입니다.");
					continue;
				
				}
					
				System.out.println("계속: 0, 종료: -1");
				end = scanner.nextInt();

			} catch (SQLException e) {
				System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
				System.out.println("사유 : " + e.getMessage());
				System.out.println("계속: 0, 종료: -1");
				end = scanner.nextInt();
			}
	       }
		}
		
		public static void main(String[] args) {
				new Batch();
				System.out.println("종료되었습니다.");

			}
		
}
