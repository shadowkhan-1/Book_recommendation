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
    private Integer pages;
    private String page;
    private String username;
    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String execute() throws Exception{
        PageBean pagebean = new PageBean();
        ActionContext context = ActionContext.getContext();
        if(this.page != null){
            this.pages = Integer.parseInt(this.page);
        }
        pagebean.setList(ServiceFactory.getUser_FavoriteInterface().findbypage(this.username,this.pages));
        pagebean.setRecommend(ServiceFactory.getBX_BooksServiceInterface().findrecommend(Integer.parseInt(username)));
        pagebean.setPages(this.pages);
        Integer totalbook = ServiceFactory.getUser_FavoriteInterface().findcount();
        pagebean.setTotalbook(totalbook);
        pagebean.setTotalpages(totalbook/pagebean.getPagesize());
        context.put("pagebean",pagebean);
        return "success";
    }
}
