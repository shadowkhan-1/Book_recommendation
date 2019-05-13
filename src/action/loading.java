package action;

import Factory.ServiceFactory;
import MD5.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import connection.DatabaseConnection;
import javabean.UserBean;
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
        ActionContext context= ActionContext.getContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user vo = new user();
        UserBean userbean = new UserBean();
        vo = ServiceFactory.getUserServiceInterface().find(username);
        if(MD5Util.md5Encode(password).equals(vo.getPassword())){
            userbean.setName(vo.getName());
            userbean.setUsername(vo.getUsername());
            userbean.setPassword(vo.getPassword());
            context.getSession().put("userbean",userbean);
            return "login";
        }
        else return "error";
    }
}
