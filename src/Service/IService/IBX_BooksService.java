package Service.IService;

import Service.Service;
import table.BX_Books;

import java.util.List;

public interface IBX_BooksService extends Service<String, BX_Books>{
    public Integer findcount() throws Exception;
    public List<BX_Books> findbypage(Integer page) throws Exception;
    public List<BX_Books> findbycount() throws Exception;
    public List<BX_Books> findrecommend(String username) throws Exception;
}
