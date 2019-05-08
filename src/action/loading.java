package action;

import MD5.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import connection.DatabaseConnection;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loading extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DatabaseConnection conn = new DatabaseConnection();
        String sql = "select password from user where username like ?";
        try{
            PreparedStatement pts = conn.getConnection().prepareStatement(sql);
            pts.setString(1,username);
            ResultSet result = pts.executeQuery();
            if(result.next()){
                if(result.getString(1).equals(MD5Util.md5Encode(password))){
                    return "login";
                }
                else {
                    return "error";
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conn.colse();
        }
        return "error";
    }
}
