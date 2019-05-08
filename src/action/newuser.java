package action;
import MD5.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import connection.DatabaseConnection;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class newuser extends ActionSupport{
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("newpassword1");
        String name = request.getParameter("name");
        DatabaseConnection conn = new DatabaseConnection();
        try{
            String sql = "insert into user(username,name,password) values(?,?,?)";
            PreparedStatement pts = conn.getConnection().prepareStatement(sql);
            pts.setString(1,username);
            pts.setString(2,name);
            pts.setString(3, MD5Util.md5Encode(password));
            if(pts.executeUpdate()>=1){
                return "success";
            }
            else return "error";
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
