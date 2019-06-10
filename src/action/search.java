package action;

import Factory.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javabean.PageBean;
import org.apache.spark.sql.sources.In;
import table.BX_Books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class search extends ActionSupport {
    private String search_name;
    private Integer page = 1;
    private String pages;
    private List<String> search_column = new ArrayList<String>(Arrays.asList("Book_Title","Book_Author","Year_Of_Publication","Publisher"));

    public Integer getPage() {
        return this.page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public String getSearch_name() {
        return this.search_name;
    }

    public void setSearch_name(final String search_name) {
        this.search_name = search_name;
    }

    public String getPages() {
        return this.pages;
    }

    public void setPages(final String pages) {
        this.pages = pages;
    }

    public String execute() throws Exception{
        if(getPages() != null){
            setPage(Integer.parseInt(getPages()));
        }
        PageBean pagebean = new PageBean();
        ActionContext context= ActionContext.getContext();
        List<BX_Books> list = ServiceFactory.getBX_BooksServiceInterface().findbykey(getPage(),this.search_column,getSearch_name());
        Integer totalpage = 0;
        for(BX_Books book:list){
              totalpage+=book.getBook_Count();
        }
        pagebean.setSearche_book(list);
        System.out.println(list.size());
//        for(BX_Books book:list){
//            System.out.println(book.getISBN());
//        }
        pagebean.setPages(getPage());
        pagebean.setTotalbook(totalpage);
        pagebean.setTotalpages(totalpage/pagebean.getPagesize());
        context.put("pagebean",pagebean);
        return "success";
    }
}
