class People {
    private String name;
    private static People people = new People();
    private static int n = 10;
    private int age = setAge();

    {
        System.out.println("初始化块");
        System.out.println("age = " + age);
    }

    static {
        System.out.println("静态块");
        System.out.println("n = " + n);
    }

    public static int setAge() {
        System.out.println("setAge:");
        return n++;
    }

    public People() {
        System.out.println("默认构造函数");
        System.out.println("age:" + age);
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("有参构造函数");
        System.out.println("age:" + age);
    }

    {
        System.out.println("初始化块1");
        System.out.println("age = " + age);
    }
}
  class Student  extends  People{

    private  static  int num=20;
    private  String school;
    {
        System.out.println("student 初始化块");
        school="河北小学";
        System.out.println(school);
    }
    static {
        System.out.println("student 静态块");
    }
    public Student() {
    }


    public Student(String name, int age) {
        super(name, age);
        System.out.println("student 构造函数");

    }
}
public class LoadingOrderTest extends Student{

    public static void main(String[] args) {
        System.out.println("main函数加载完成");
        Student p = new Student();
        Student p1=new Student("zhangsan",40);

    }
}



