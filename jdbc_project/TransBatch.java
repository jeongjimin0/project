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

public class TransBatch {
	
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	
	public TransBatch() {
		
        String resource = "C:\\Users\\정지민\\Desktop\\test\\File search\\src\\db.properties";
        String resource2 = "C:\\Users\\정지민\\Desktop\\test\\File search\\src\\batch_file\\query.xml";
        Properties properties = new Properties();
        Properties properties2 = new Properties();
	
        try {
            properties.load(new FileInputStream(resource));
            properties2.loadFromXML(new FileInputStream(resource2));
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		try {
			
            String user = properties.getProperty("user");
            String passwd = properties.getProperty("passwd");
            String url = properties.getProperty("url");
            String sql = properties2.getProperty("selectinsert");
            String sqld = properties2.getProperty("delete");
			
	        con = DriverManager.getConnection(url, user, passwd);
			System.out.println("DB연결 성공");
			
			System.out.println("이관할 업무 분류를 입력하세요");
			Scanner scanner = new Scanner(System.in);
			String code = scanner.nextLine();
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+code+"%");
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 삽입되었습니다.");
			
			
		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.println("사유 : " + e.getMessage());
			System.out.println("계속: 0, 종료: -1");
		}
	
	}
	
	public static void main(String[] args) {
		new TransBatch();
		System.out.println("종료되었습니다.");

	}

}
