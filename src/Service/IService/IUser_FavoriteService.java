package Service.IService;

import Service.Service;
import table.BX_Books;
import table.User_Favorite;

import java.util.List;

public interface IUser_FavoriteService extends Service<String, User_Favorite> {
    public boolean delete(Integer id) throws Exception;
    public boolean findexist(User_Favorite vo) throws Exception;
    public List<BX_Books> findbypage(String username, Integer pages) throws Exception;
    public Integer findcount() throws Exception;
}
