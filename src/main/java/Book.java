/**
 * Project name(项目名称)：反射访问构造方法
 * Package(包名): PACKAGE_NAME
 * Class(类名): Book
 * Author(作者）: mao
 * Author QQ：1296193245
 * Date(创建日期)： 2021/10/26
 * Time(创建时间)： 18:23
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Book
{
    String name; // 图书名称
    int id, price; // 图书编号和价格

    // 空的构造方法
    private Book()
    {

    }

    // 带两个参数的构造方法
    protected Book(String _name, int _id)
    {
        this.name = _name;
        this.id = _id;
    }

    // 带可变参数的构造方法
    public Book(String... strings) throws NumberFormatException
    {
        if (0 < strings.length)
        {
            id = Integer.valueOf(strings[0]);
        }
        if (1 < strings.length)
        {
            price = Integer.valueOf(strings[1]);
        }
    }

    // 输出图书信息
    public void print()
    {
        System.out.println("name=" + name);
        System.out.println("id=" + id);
        System.out.println("price=" + price);
    }
}
