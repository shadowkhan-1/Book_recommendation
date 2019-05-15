package Service.IService;

import Service.Service;
import table.User_Favorite;

public interface IUser_FavoriteService extends Service<String, User_Favorite> {
    public boolean delete(Integer id) throws Exception;
}
