import connection.DatabaseConnection;

import java.sql.Connection;

public class test {
    public static void main(String args[]){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connection = conn.getConnection();
        System.out.println(connection);
    }
}
