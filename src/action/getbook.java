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

public class getbook extends ActionSupport {
    public String execute() throws Exception{
        PageBean pagebean = new PageBean();
        Integer pages = 1;
        ActionContext context= ActionContext.getContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        String _pages = request.getParameter("pages");
        List<BX_Books> list = new ArrayList<BX_Books>();
        if(_pages!=null){
            pages = Integer.parseInt(_pages);
        }
        list = ServiceFactory.getBX_BooksServiceInterface().findbypage(pages);
        Integer totalbook = ServiceFactory.getBX_BooksServiceInterface().findcount();
        pagebean.setList(list);
        pagebean.setTotalbook(totalbook);
        pagebean.setPages(pages);
        pagebean.setTotalpages(totalbook / pagebean.getPagesize());
        context.put("pagebean", pagebean);
        return "success";
    }
}
