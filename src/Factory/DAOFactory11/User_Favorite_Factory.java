package Factory.DAOFactory11;

import DAO.IDAO.IDAOImplement.User_FavoriteDAOImp;
import DAO.IDAO.IUser_FavoriteDAO;

import java.sql.Connection;

public class User_Favorite_Factory implements Factory {
    @Override
    public IUser_FavoriteDAO getDAO(Connection conn) {
        return new User_FavoriteDAOImp(conn);
    }
}
