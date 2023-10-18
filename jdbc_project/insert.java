package batch_file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert {
	
	public void ins() {
        Connection con = null;                                     // 데이터 베이스와 연결을 위한 객체
        PreparedStatement pstmt = null;                            // SQL 문을 데이터베이스에 보내기위한 객체

        // 1. JDBC Driver Class - com.mysql.jdbc.Driver
        String driver = "oracle.jdbc.driver.OracleDriver";

        // 2. 데이터베이스에 연결하기 위한 정보
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";       // 연결문자열
        String user = "c##project_2_table_";                                     // 데이터베이스 ID
        String pw = "1234";                                  // 데이터베이스 PW

        String SQL = "insert into ANSWER(ID, CONTENT, CREATEDATE, QUESTION) values(?, ?, ?, ?)";

        try {
            // 1. JDBC 드라이버 로딩 - MySQL JDBC 드라이버의 Driver Class 로딩
            Class.forName(driver);

            // 2. Connection 생성 - .getConnection(연결문자열, DB-ID, DB-PW)
            con = DriverManager.getConnection(url, user, pw);

            // 3. PreParedStatement 객체 생성, 객체 생성시 SQL 문장 저장
            pstmt = con.prepareStatement(SQL);

            // 4. pstmt.set<데이터타입>(? 순서, 값) ex).setString(), .setInt ...
            pstmt.setInt(1, 4);
            pstmt.setString(2, "댓글...!");
            pstmt.setString(3, "23/10/16");
            pstmt.setInt(4, 4);

            // 5. SQL 문장을 실행하고 결과를 리턴 - SQL 문장 실행 후, 변경된 row 수 int type 리턴
            int r = pstmt.executeUpdate();

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete ..

            System.out.println("변경된 row : " + r);

        } catch (SQLException e) {

            System.out.println("[SQL Error : " + e.getMessage() + "]");

        } catch (ClassNotFoundException e1) {

            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

        } finally {

            //사용순서와 반대로 close 함
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	
	
	public static void main(String[] args) {
		insert ins = new insert();
		ins.ins();
	}
	

}
