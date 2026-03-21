import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeDAO {

  private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());
  private static final String DB_URL ="jdbc:postgresql://localhost:5432/practice";
  private static final String DB_USER ="yamagiwamasaya";
  private static final String DB_PASSWORD ="";

  //全社員取得
  public List<Employee> findALL(){
    List<Employee> list =  new ArrayList<>();
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM 社員");
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()){
        Employee emp = new Employee(
            rs.getInt("id"),
            rs.getString("名前"),
            rs.getInt("年齢")
        );
        list.add(emp);
      }

    } catch (Exception e){
      logger.severe("エラー：" + e.getMessage());
    } finally {
      if (conn != null){
        try {
          conn.close();
        }catch (Exception e){
          logger.severe("切断失敗：" + e.getMessage());
        }
      }
    }

    return list;
  }

  //社員登録
  public void insert(Employee emp) {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      PreparedStatement pstmt = conn.prepareStatement(
          "INSERT INTO 社員 (名前, 年齢) VALUES (?,?)"
      );
      pstmt.setString(1, emp.getName());
      pstmt.setInt(2, emp.getAge());
      pstmt.executeUpdate();
      logger.info("登録しました：" + emp.getName());

    } catch (Exception e){
      logger.severe("エラー：" + e.getMessage());
    } finally {
      if (conn != null){
        try {
          conn.close();
        } catch (Exception e){
          logger.severe("切断失敗：" + e.getMessage());
        }
      }
    }
  }

  //社員削除
  public void delete(String name){
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      PreparedStatement pstmt = conn.prepareStatement(
          "DELETE FROM 社員 WHERE 名前 = ?"
      );
      pstmt.setString(1, name);
      pstmt.executeUpdate();
      logger.info("削除しました：" + name);

    } catch (Exception e){
      logger.severe("エラー：" + e.getMessage());
    } finally {
      if (conn != null){
        try {
          conn.close();
        } catch (Exception e){
          logger.severe("切断失敗：" + e.getMessage());
        }
      }
    }
  }
}
