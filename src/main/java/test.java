import java.lang.reflect.Constructor;

/**
 * Project name(项目名称)：反射访问构造方法
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * Date(创建日期)： 2021/10/26
 * Time(创建时间)： 18:12
 * Version(版本): 1.0
 * Description(描述)： 为了能够动态获取对象构造方法的信息，首先需要通过下列方法之一创建一个 Constructor 类型的对象或者数组。
 * getConstructors()
 * getConstructor(Class<>…parameterTypes)
 * getDeclaredConstructors()
 * getDeclaredConstructor(Class<>...parameterTypes)
 * 如果是访问指定的构造方法，需要根据该构造方法的入口参数的类型来访问。例如，访问一个入口参数类型依次为 int 和 String 类型的构造方法，下面的两种方式均可以实现。
 * objectClass.getDeclaredConstructor(int.class,String.class);
 * objectClass.getDeclaredConstructor(new Class[]{int.class,String.class});
 * 方法名称	说明
 * isVarArgs()	查看该构造方法是否允许带可变数量的参数，如果允许，返回 true，否则返回
 * false
 * getParameterTypes()	按照声明顺序以 Class 数组的形式获取该构造方法各个参数的类型
 * getExceptionTypes()	以 Class 数组的形式获取该构造方法可能抛出的异常类型
 * newInstance(Object … initargs)	通过该构造方法利用指定参数创建一个该类型的对象，如果未设置参数则表示采用默认无参的构造方法
 * setAccessiable(boolean flag)	如果该构造方法的权限为 private，默认为不允许通过反射利用 netlnstance()
 * 方法创建对象。如果先执行该方法，并将入口参数设置为 true，则允许创建对象
 * getModifiers()	获得可以解析出该构造方法所采用修饰符的整数
 * Modifier类的常用方法
 * 静态方法名称	说明
 * isStatic(int mod)	如果使用 static 修饰符修饰则返回 true，否则返回 false
 * isPublic(int mod)	如果使用 public 修饰符修饰则返回 true，否则返回 false
 * isProtected(int mod)	如果使用 protected 修饰符修饰则返回 true，否则返回 false
 * isPrivate(int mod)	如果使用 private 修饰符修饰则返回 true，否则返回 false
 * isFinal(int mod)	如果使用 final 修饰符修饰则返回 true，否则返回 false
 * toString(int mod)	以字符串形式返回所有修饰符
 */

public class test
{
    public static void main(String[] args)
    {
        //------------------------------------------------------
        long startTime = System.nanoTime();   //获取开始时间
        //------------------------------------------------------
// 获取动态类Book
        Class<Book> book = Book.class;
        // 获取Book类的所有构造方法
        Constructor[] declaredContructors = book.getDeclaredConstructors(); //获得声明的构造函数
        // 遍历所有构造方法
        for (int i = 0; i < declaredContructors.length; i++)
        {
            Constructor con = declaredContructors[i];
            // 判断构造方法的参数是否可变
            System.out.println("查看是否允许带可变数量的参数：" + con.isVarArgs());
            System.out.println("该构造方法的入口参数类型依次为：");
            // 获取所有参数类型
            Class[] parameterTypes = con.getParameterTypes();  //获取参数类型
            for (int j = 0; j < parameterTypes.length; j++)
            {
                System.out.println(" " + parameterTypes[j]);
            }
            System.out.println("该构造方法可能拋出的异常类型为：");
            // 获取所有可能拋出的异常类型
            Class[] exceptionTypes = con.getExceptionTypes();
            for (int j = 0; j < exceptionTypes.length; j++)
            {
                System.out.println(" " + parameterTypes[j]);
            }
            // 创建一个未实例化的Book类实例
            Book book1 = null;
            while (book1 == null)
            {
                try
                { // 如果该成员变量的访问权限为private，则拋出异常
                    if (i == 1)
                    {
                        // 通过执行带两个参数的构造方法实例化book1
                        book1 = (Book) con.newInstance("hello", 10);
                    }
                    else if (i == 2)
                    {
                        // 通过执行默认构造方法实例化book1
                        book1 = (Book) con.newInstance();
                    }
                    else
                    {
                        // 通过执行可变数量参数的构造方法实例化book1
                        Object[] parameters = new Object[]{new String[]{"112", "4687"}};
                        book1 = (Book) con.newInstance(parameters);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("在创建对象时拋出异常，下面执行 setAccessible() 方法");
                    con.setAccessible(true); // 设置允许访问 private 成员
                }
            }
            book1.print();
        }
        //------------------------------------------------------
        long endTime = System.nanoTime(); //获取结束时间
        if ((endTime - startTime) < 1000000)
        {
             double final_runtime;
             final_runtime = (endTime - startTime);
             final_runtime = final_runtime / 1000;
             System.out.println("算法运行时间： " + final_runtime + "微秒");
        }
        else if ((endTime - startTime) >= 1000000 && (endTime - startTime) < 10000000000L)
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 1000;
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "毫秒");
         }
        else
        {
             double final_runtime;
             final_runtime = (endTime - startTime) / 10000;
             final_runtime = final_runtime / 100000;
             System.out.println("算法运行时间： " + final_runtime + "秒");
        }
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        //------------------------------------------------------
    }
}
