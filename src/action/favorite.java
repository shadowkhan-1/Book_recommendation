package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import table.User_Favorite;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class favorite extends ActionSupport {
//    // 创建私有变量
//    private InputStream streamActionResult = null;
//    // struts.xml 定义返回
//    public InputStream getStreamActionResult()
//    {
//        return streamActionResult;
//    }
     private String username;               //struts用属性驱动来获取数据
     private String ISBN;
     private InputStream message;                               //inputName的名称
     public InputStream getMessage(){return  message;}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getISBN() {
        return this.ISBN;
    }
    public void setISBN(final String ISBN) {
        this.ISBN = ISBN;
    }

    public String execute() throws Exception{
            User_Favorite vo =new User_Favorite();
            vo.setUsername(username);
            vo.setISBN(ISBN);
            StringBuffer themessage;
            if(ServiceFactory.getUser_FavoriteInterface().insert(vo)){
                themessage = new StringBuffer("success");
                this.message = new ByteArrayInputStream(themessage.toString().getBytes("utf-8"));
                return "success";
            }
            else {
                themessage = new StringBuffer("error");
                this.message = new ByteArrayInputStream(themessage.toString().getBytes("utf-8"));
                return  "error";
            }
    }
}
