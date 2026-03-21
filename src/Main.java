import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args){

    EmployeeService service = new EmployeeService();
    Scanner scanner = new Scanner(System.in);

    int input = 0;
    do {
      System.out.println("=== 社員管理アプリ ===");
      System.out.println("1: 社員一覧表示");
      System.out.println("2: 社員登録");
      System.out.println("3: 社員削除");
      System.out.println("0: 終了");
      System.out.print("番号を入力してください：");
      input = scanner.nextInt();

      if (input == 1){
        //一覧表示
        List<Employee> list = service.getALLEmployees();
        if (list.isEmpty()){
          System.out.println("社員が登録されていません");
        } else {
          for (Employee emp : list){
            System.out.println(emp.getId() + "｜" + emp.getName() + "｜" + emp.getAge() + "歳");
          }
        }

      } else if (input == 2){
        //社員登録
        System.out.print("名前を入力してください：");
        String name = scanner.next();
        System.out.print("年齢を入力してください：");
        int age = scanner.nextInt();
        service.addEmployee(name, age);
        System.out.println((name + "さんを登録しました"));

      } else if (input == 3){
        //社員の削除
        System.out.print("名前を入力してください：");
        String name = scanner.next();
        service.removeEmployee(name);
        System.out.println(name + "さんを削除しました");

      } else if (input == 0) {
        System.out.println("終了します");
      }

    } while (input != 0);
  }
}