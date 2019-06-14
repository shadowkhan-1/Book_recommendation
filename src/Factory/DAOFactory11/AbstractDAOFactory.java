package Factory.DAOFactory11;

import DAO.DAO;
import Service.Service;

import java.sql.Connection;

public abstract class AbstractDAOFactory {
    public abstract DAO getDAO(String name, Connection conn);
}
