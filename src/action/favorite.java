package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import table.User_Favorite;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class favorite extends ActionSupport {
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String data = request.getParameter("data");
        System.out.println(data);
        response.getWriter().write("success");
        return "success";

//            String username = request.getParameter("username");
//            String ISBN = request.getParameter("ISBN");
//            User_Favorite vo = new User_Favorite();
//            vo.setISBN(ISBN);
//            vo.setUsername(username);
//            if(ServiceFactory.getUser_FavoriteInterface().insert(vo)){
//                return "success";
//            }
//            else{
//                return "error";
//            }
    }
}
