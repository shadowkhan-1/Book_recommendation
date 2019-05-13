package Factory;

import DAO.IDAO.IBX_BooksDAO;
import DAO.IDAO.IDAOImplement.BX_BooksDAOImp;
import DAO.IDAO.IDAOImplement.UserDAOImp;
import DAO.IDAO.IUserDAO;

import java.sql.Connection;

public class DAOFactory {
    public static IUserDAO getUserIDAOInterface(Connection conn){
        return new UserDAOImp(conn);
    }
    public static IBX_BooksDAO getBX_BooksDAOInterface(Connection conn){
        return new BX_BooksDAOImp(conn);
    }
}
