package Service.IService.IServiceImplement;

import Factory.DAOFactory;
import Service.IService.IBX_BooksService;
import connection.DatabaseConnection;
import org.apache.derby.jdbc.EmbeddedXADataSource;
import table.BX_Books;

import java.util.List;

public class BX_BooksServiceImp implements IBX_BooksService {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public Integer findcount() throws Exception {
        try{
            return DAOFactory.getBX_BooksDAOInterface(dbc.getConnection()).FindCount();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<BX_Books> findbypage(Integer page) throws Exception {
        try{
            return DAOFactory.getBX_BooksDAOInterface(dbc.getConnection()).FindByPage(page);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<BX_Books> findbycount() throws Exception {
        try{
            return DAOFactory.getBX_BooksDAOInterface(dbc.getConnection()).FindByCount();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<BX_Books> findrecommend(String username) throws Exception {
        try{
            return DAOFactory.getBX_BooksDAOInterface(dbc.getConnection()).FindRecommend(username);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<BX_Books> findbykey(Integer page, List<String> columns, String key) throws Exception {
        try{
           return DAOFactory.getBX_BooksDAOInterface(dbc.getConnection()).FindByKey(page,columns,key);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public boolean insert(BX_Books vo) throws Exception {
        return false;
    }

    @Override
    public boolean update(BX_Books vo) throws Exception {
        return false;
    }

    @Override
    public BX_Books find(String key) throws Exception {
        return null;
    }

    @Override
    public List<BX_Books> findall() throws Exception {
        return null;
    }
}
