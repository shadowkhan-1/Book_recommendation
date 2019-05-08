package action;

import Factory.ServiceFactory;
import MD5.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import connection.DatabaseConnection;
import org.apache.struts2.ServletActionContext;
import table.user;

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
        user vo = new user();
        vo = ServiceFactory.getUserServiceInterface().find(username);
        if(MD5Util.md5Encode(password).equals(vo.getPassword())){
            return "login";
        }
        else return "error";
    }
}
