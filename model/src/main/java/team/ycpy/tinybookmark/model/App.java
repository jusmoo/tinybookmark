package team.ycpy.tinybookmark.model;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConnectServer con = new ConnectServer("root","Aaron12345");
        int flag = con.link();
        if(flag == 200)System.out.println("link successfully");
        else System.out.println("Failed");
        User use = new User();
        use.setusername("Aaron");
        use.setnickname("wiki");
        System.out.println(use.queryUser(con, " * ").toString());
        System.out.println(use.insertUser(con).toString());
        System.out.println(use.updateUser(con).toString());
        System.out.println(use.insertUser(con).toString());
        System.out.println(use.deleteUser(con).toString());
    }
}
