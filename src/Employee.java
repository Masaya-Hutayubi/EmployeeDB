public class Employee {

  private int id;
  private String name;
  private int age;

  @Override
  public String toString(){
    return id + "｜" + name + "｜" + age + "歳" ;
  }

  //コンストラクタ
  public Employee(int id, String name, int age){
    this.id = id;
    this.name = name;
    this.age = age;
  }

  //getter
  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public int getAge(){
    return age;
  }

}
