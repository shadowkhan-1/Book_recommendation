package Service.IService.IServiceImplement;

import Factory.DAOFactory;
import Service.IService.IUserService;
import connection.DatabaseConnection;
import table.user;

import java.util.List;

public class UserServiceImp implements IUserService {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(user vo) throws Exception {
        try{
            if(DAOFactory.getUserIDAOInterface(dbc.getConnection()).Find(vo.getUsername())!=null){
                return DAOFactory.getUserIDAOInterface(dbc.getConnection()).Create(vo);
            }
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
        return false;
    }

    @Override
    public boolean update(user vo) throws Exception {
        try{
                return DAOFactory.getUserIDAOInterface(dbc.getConnection()).Update(vo);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public user find(String username) throws Exception {
        try{
            return DAOFactory.getUserIDAOInterface(dbc.getConnection()).Find(username);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<user> findall() throws Exception {
        return null;
    }
}
