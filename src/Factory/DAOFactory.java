package Factory;

import DAO.IDAO.IBX_BooksDAO;
import DAO.IDAO.IDAOImplement.BX_BooksDAOImp;
import DAO.IDAO.IDAOImplement.UserDAOImp;
import DAO.IDAO.IDAOImplement.User_FavoriteDAOImp;
import DAO.IDAO.IUserDAO;
import DAO.IDAO.IUser_FavoriteDAO;

import java.sql.Connection;

public class DAOFactory {
    public static IUserDAO getUserIDAOInterface(Connection conn){
        return new UserDAOImp(conn);
    }
    public static IBX_BooksDAO getBX_BooksDAOInterface(Connection conn){
        return new BX_BooksDAOImp(conn);
    }
    public static IUser_FavoriteDAO getUser_FavoriteDAOInterface(Connection conn){
        return  new User_FavoriteDAOImp(conn);
    }
}
