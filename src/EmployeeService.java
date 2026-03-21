import java.util.List;
import java.util.logging.Logger;

public class EmployeeService {

  private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());
  private EmployeeDAO dao = new EmployeeDAO();

  //全社員取得
  public List<Employee> getALLEmployees(){
    return dao.findALL();
  }

  //社員登録
  public void addEmployee(String name, int age){
    if (name == null || name.isEmpty()){
      logger.warning("名前が空です");
      return;
    }
    if (age < 0 || age > 100 ){
      logger.warning("年齢が不正です" + age);
      return;
    }
    Employee emp = new Employee(0, name, age);
    dao.insert(emp);
  }

  //社員の削除
  public void removeEmployee(String name){
    if (name == null || name.isEmpty()){
      logger.warning("名前が空です");
      return;
    }
    dao.delete(name);
  }

}


