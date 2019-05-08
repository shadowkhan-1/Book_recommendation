package Factory;

import DAO.IDAO.IDAOImplement.UserIDAOImp;
import DAO.IDAO.IUserDAO;

import java.sql.Connection;

public class DAOFactory {
    public static IUserDAO getUserIDAOInterface(Connection conn){
        return new UserIDAOImp(conn);
    }
}
