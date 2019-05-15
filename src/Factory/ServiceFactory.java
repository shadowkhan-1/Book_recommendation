package Factory;

import Service.IService.IBX_BooksService;
import Service.IService.IServiceImplement.BX_BooksServiceImp;
import Service.IService.IServiceImplement.UserServiceImp;
import Service.IService.IServiceImplement.User_FavoriteServiceImp;
import Service.IService.IUserService;
import Service.IService.IUser_FavoriteService;

import java.sql.Connection;

public class ServiceFactory {
    public static IUserService getUserServiceInterface(){
        return new UserServiceImp();
    }
    public static IBX_BooksService getBX_BooksServiceInterface(){
        return new BX_BooksServiceImp();
    }
    public static IUser_FavoriteService getUser_FavoriteInterface(){
        return new User_FavoriteServiceImp();
    }
}
