import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
public class Main {
    public static void main(String[] args) {

      String url = "jdbc:postgresql://localhost:5432/practice";
      String user = "yamagiwamasaya";
      String password = "";

      Connection conn = null;

      try {
        //DB接続
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("DB接続成功！");

        //SQL実行
        String sql = " INSERT INTO 社員 (名前, 年齢, 部署id, 入社日)  " +
                     " VALUES (?, ?, ?, ?)" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"山田太郎");
        pstmt.setInt(2,34);
        pstmt.setInt(3,1);
        pstmt.setDate(4, Date.valueOf("2018-04-01"));
        pstmt.executeUpdate();

        //確認用SELECT
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM 社員");
        ResultSet rs = pstmt2.executeQuery();
        while (rs.next()){
          System.out.println(rs.getInt("id") + " | " + rs.getString("名前") + " | " + rs.getInt("年齢") + "歳");
        }

      } catch (Exception e) {
        System.out.println("エラー" + e.getMessage());
      } finally {
          if (conn != null){
            try {
              conn.close();
            } catch (Exception e) {
                System.out.println("切断失敗" + e.getMessage());
            }
          }

      }

    }
}