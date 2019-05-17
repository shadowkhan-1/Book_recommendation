package DAO.IDAO;

import DAO.DAO;
import table.User_Favorite;

import java.util.List;

public interface IUser_FavoriteDAO extends DAO<String, User_Favorite> {
    public boolean Delete(Integer id) throws Exception;
    public boolean FindExist(User_Favorite vo) throws Exception;
    public List<User_Favorite> FindByPage(String username,Integer pages) throws Exception;
}
