package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import table.User_Favorite;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class favorite extends ActionSupport {
    // 创建私有变量
    private InputStream streamActionResult = null;
    // struts.xml 定义返回
    public InputStream getStreamActionResult()
    {
        return streamActionResult;
    }
    public String execute() throws Exception{
        streamActionResult = new ByteArrayInputStream("success".getBytes("UTF-8"));
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String data = request.getParameter("data");
        System.out.println(data);
        String strResult = "success";
        return strResult;

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
