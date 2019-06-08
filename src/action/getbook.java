package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javabean.PageBean;
import javabean.UserBean;
import org.apache.struts2.ServletActionContext;
import table.BX_Books;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class getbook extends ActionSupport {
    public String execute() throws Exception{
        PageBean pagebean = new PageBean();
        Integer pages = 1;
        ActionContext context= ActionContext.getContext();
        Map<String,Object> session = context.getSession();
        UserBean userbean = (UserBean) session.get("userbean");
        HttpServletRequest request = ServletActionContext.getRequest();
        String _pages = request.getParameter("pages");
        List<BX_Books> list = new ArrayList<BX_Books>();
        List<BX_Books> book_list = new ArrayList<BX_Books>();
        if(_pages!=null){
            pages = Integer.parseInt(_pages);
        }
        list = ServiceFactory.getBX_BooksServiceInterface().findbypage(pages);
        book_list = ServiceFactory.getBX_BooksServiceInterface().findbycount();
        Integer totalbook = ServiceFactory.getBX_BooksServiceInterface().findcount();
        pagebean.setRecommend(ServiceFactory.getBX_BooksServiceInterface().findrecommend(Integer.parseInt(userbean.getUsername())));
        pagebean.setList(list);
        pagebean.setTotalbook(totalbook);
        pagebean.setPages(pages);
        pagebean.setBook_list(book_list);
        pagebean.setTotalpages(totalbook / pagebean.getPagesize());
        context.put("pagebean", pagebean);
        return "success";
    }
}
