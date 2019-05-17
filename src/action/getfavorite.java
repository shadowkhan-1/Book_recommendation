package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javabean.PageBean;
import org.apache.struts2.ServletActionContext;
import table.BX_Books;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class getfavorite extends ActionSupport {
    private Integer pages = 1;
    private String username;
    public Integer getPages() {
        return this.pages;
    }

    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String execute() throws Exception{
        PageBean pagebean = new PageBean();
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        List<BX_Books> list = new ArrayList<BX_Books>();
        return "success";
    }
}
