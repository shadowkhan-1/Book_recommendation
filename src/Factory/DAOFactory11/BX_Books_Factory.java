package Factory.DAOFactory11;

import DAO.IDAO.IBX_BooksDAO;
import DAO.IDAO.IDAOImplement.BX_BooksDAOImp;

import java.sql.Connection;

public class BX_Books_Factory implements Factory{
    @Override
    public IBX_BooksDAO getDAO(Connection conn) {
        return new BX_BooksDAOImp(conn);
    }
}
