import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DeleteBatch {

    public static void main(String[] args) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("./conf/db.properties");
            Properties prop = new Properties();
            prop.load(fis);

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = prop.getProperty("url");
            String id = prop.getProperty("user");
            String pw = prop.getProperty("passwd");
            String date = prop.getProperty("date");
            String num = prop.getProperty("num");
            System.out.println("DB 연결 시도...");

            con = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 연결 성공!");

            int batchSize = 100; // 한 번에 처리할 데이터 행 수
            int totalCount = 0; // 전체 데이터 처리 횟수
            boolean hasMoreData = true; // 데이터가 더 남아 있는지 여부

            while (hasMoreData) {
                String sql = "SELECT ELEMENTID FROM asyscontentelement WHERE elementid LIKE ? AND ROWNUM <= ?";
                psmt = con.prepareStatement(sql);
                psmt.setString(1, date + "%");
                psmt.setInt(2, Integer.parseInt(num));
                rs = psmt.executeQuery();

                int count = 0; // 현재 반복에서 처리된 데이터 수

                // 데이터 처리
                while (rs.next()) {
                    String elementId = rs.getString("ELEMENTID");
                    deleteElementById(con, elementId); // 데이터베이스에서 해당 element 삭제
                    count++;
                }

                totalCount += count;
                System.out.println("총 " + count + "개의 데이터 삭제 완료.");

                if (count < batchSize) {
                    // 한 번에 처리한 데이터가 batchSize보다 작으면 더 이상 데이터가 없다고 가정
                    hasMoreData = false;
                }
            }

            System.out.println("총 " + totalCount + "개의 데이터 삭제 완료.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB 연결 또는 쿼리 실행 중 오류 발생");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("파일을 찾을 수 없음");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 중 오류 발생");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC 드라이버 로드 중 오류 발생");
        } finally {
            // 리소스 정리
            try {
                if (rs != null) rs.close();
                if (psmt != null) psmt.close();
                if (con != null) con.close();
                if (fis != null) fis.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 데이터베이스에서 해당 ID의 element 삭제
    private static void deleteElementById(Connection con, String elementId) throws SQLException {
        String sqlDelete = "DELETE FROM asyscontentelement WHERE elementid = ?";
        try (PreparedStatement psmt = con.prepareStatement(sqlDelete)) {
            psmt.setString(1, elementId);
            psmt.executeUpdate();
        }
        System.out.println("Element ID " + elementId + " 삭제 완료.");
    }
}