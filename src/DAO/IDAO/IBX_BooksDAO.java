package DAO.IDAO;

import DAO.DAO;
import table.BX_Books;

import java.util.List;

public interface IBX_BooksDAO extends DAO<String, BX_Books> {
    public BX_Books FindByKey(String column,String key) throws Exception;//按列跟列的值
    public List<BX_Books> FindByPage(Integer page) throws Exception;//分页查询
    public Integer FindCount() throws Exception;
}
