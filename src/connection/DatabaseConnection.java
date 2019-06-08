package connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements Serializable {
        private static final String driver = "com.mysql.jdbc.Driver";
        private static final String url = "jdbc:mysql://localhost:3306/book_recommendation?characterEncoding=utf8&useSSL=false&";
        private static final String user="linwei";
        private static final String password ="123456";//window-computer
//        private static final String password="Mysql#2018";//ubuntu-computer
        private Connection conn=null;
        //构造方法连接数据库
        public DatabaseConnection(){
            try{
                Class.forName(driver);
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                this.conn= DriverManager.getConnection(url,user,password);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        //获取一个数据库连接对象
        public Connection getConnection(){
            return this.conn;
        }
        //数据库的关闭
        public void colse(){
            if(this.conn!=null){
                try{
                    this.conn.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
}
