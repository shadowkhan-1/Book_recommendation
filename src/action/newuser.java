package action;
import Factory.ServiceFactory;
import MD5.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import connection.DatabaseConnection;
import org.apache.struts2.ServletActionContext;
import table.user;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class newuser extends ActionSupport{
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("newpassword1");
        String name = request.getParameter("name");
        user vo = new user();
        vo.setUsername(username);
        vo.setName(name);
        vo.setPassword(MD5Util.md5Encode(password));
        if(ServiceFactory.getUserServiceInterface().insert(vo)){
            return "success";
        }
        else {
            return "error";
        }
    }
}
