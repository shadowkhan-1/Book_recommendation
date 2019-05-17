package Service.IService.IServiceImplement;

import Factory.DAOFactory;
import Service.IService.IUser_FavoriteService;
import connection.DatabaseConnection;
import table.User_Favorite;

import java.sql.PreparedStatement;
import java.util.List;

public class User_FavoriteServiceImp implements IUser_FavoriteService {
    DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean delete(Integer id) throws Exception {
        try{
            return DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).Delete(id);
        }
        catch (Exception e){
            throw  e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public boolean findexist(User_Favorite vo) throws Exception {
        try{
            return DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).FindExist(vo);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<User_Favorite> findbypage(String username, Integer pages) throws Exception {
        try{
            return DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).FindByPage(username,pages);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public boolean insert(User_Favorite vo) throws Exception {
        try{
            if(DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).FindExist(vo)){
                return false;
            }
            else {
                return DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).Create(vo);
            }
        }
        catch (Exception e){
            throw e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public boolean update(User_Favorite vo) throws Exception {
        return false;
    }

    @Override
    public User_Favorite find(String username) throws Exception {
        try{
           return DAOFactory.getUser_FavoriteDAOInterface(dbc.getConnection()).Find(username);
        }
        catch (Exception e){
            throw  e;
        }
        finally {
            dbc.colse();
        }
    }

    @Override
    public List<User_Favorite> findall() throws Exception {
        return null;
    }
}
