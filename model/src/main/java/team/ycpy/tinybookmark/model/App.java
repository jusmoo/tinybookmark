package team.ycpy.tinybookmark.model;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //ConnectServer con = new ConnectServer("root","Aaron12345");
    	ConnectServer con = new ConnectServer("root","root");
        int flag = con.link();
        if(flag == 200)System.out.println("link successfully");
        else System.out.println("Failed");
        User use = new User();
        use.setusername("Aaron");
        use.setnickname("wiki");
        use.setrootfoldid("201601");
        con.executeInsert(use.insertUser(con));
        System.out.println(use.queryUser(con, " * ").toString());
        System.out.println(use.insertUser(con).toString());
        System.out.println(use.updateUser(con).toString());
        System.out.println(use.insertUser(con).toString());
        System.out.println(use.deleteUser(con).toString());
        
        Item itemtest = new Item();
        itemtest.setItype(1);
        itemtest.setIno("10001");
        itemtest.setIname("MIT Discrete");
        System.out.println(itemtest.queryItem(con, " * "));
        System.out.println(itemtest.deleteItem(con));
        System.out.println(itemtest.insertItem(con));
        System.out.println(itemtest.updateItem(con));
        
        
    }
}
