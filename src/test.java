import MD5.MD5Util;
import connection.DatabaseConnection;
import java.sql.Connection;

public class test {
    public static void main(String args[]) throws Exception{
/*        DatabaseConnection conn = new DatabaseConnection();
        Connection connection = conn.getConnection();
        System.out.println(connection);//*/
        System.out.println(MD5Util.md5Encode("123456"));
        String passoword = MD5Util.md5Encode("123456");
        System.out.println(MD5Util.md5Encode(passoword));
    }
}
