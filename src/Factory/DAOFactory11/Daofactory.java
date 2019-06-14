package Factory.DAOFactory11;
import DAO.DAO;
import Factory.DAOFactory11.AbstractDAOFactory;
import Factory.DAOFactory11.BX_Books_Factory;
import Factory.DAOFactory11.UserFacotry;
import Factory.DAOFactory11.User_Favorite_Factory;

import java.sql.Connection;

public class Daofactory extends AbstractDAOFactory {
    public DAO getDAO(String name, Connection conn){
        if("User".equalsIgnoreCase(name)){
            return new UserFacotry().getDAO(conn);
        }
        else if("BX_Books".equalsIgnoreCase(name)){
            return new BX_Books_Factory().getDAO(conn);
        }
        else if("User_Favorite".equalsIgnoreCase(name)){
            return new User_Favorite_Factory().getDAO(conn);
        }
        else {
            System.out.println("参数输入错误或者没有这个DAO类！");
            return null;
        }
    }
}
