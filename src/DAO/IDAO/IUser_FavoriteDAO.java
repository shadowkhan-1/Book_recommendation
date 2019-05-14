package DAO.IDAO;

import DAO.DAO;
import table.User_Favorite;

public interface IUser_FavoriteDAO extends DAO<String, User_Favorite> {
    public boolean delete(Integer id) throws Exception;
}
