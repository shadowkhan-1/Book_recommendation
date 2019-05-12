import Factory.ServiceFactory;
import MD5.MD5Util;
import connection.DatabaseConnection;
import table.BX_Books;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String args[]) throws Exception{
/*        DatabaseConnection conn = new DatabaseConnection();
        Connection connection = conn.getConnection();
        System.out.println(connection);//*/
//        System.out.println(MD5Util.md5Encode("123456"));
//        String passoword = MD5Util.md5Encode("123456");
//        System.out.println(MD5Util.md5Encode(passoword));
        List<BX_Books> list = new ArrayList<BX_Books>();
        list = ServiceFactory.getBX_BooksServiceInterface().findbypage(2);
        for(BX_Books i:list){
            System.out.println(i.getBook_Author());
        }
    }
}
