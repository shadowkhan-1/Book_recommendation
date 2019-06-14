package Factory.DAOFactory11;

import DAO.DAO;

import java.sql.Connection;

public interface Factory {
    DAO getDAO(Connection conn);
}
