package Factory.DAOFactory11;

import DAO.IDAO.IDAOImplement.UserDAOImp;
import DAO.IDAO.IUserDAO;

import java.sql.Connection;

public class UserFacotry implements Factory {
    @Override
    public IUserDAO getDAO(Connection conn) {
        return new UserDAOImp(conn);
    }
}
