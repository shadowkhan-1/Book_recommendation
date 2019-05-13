package Factory;

import Service.IService.IBX_BooksService;
import Service.IService.IServiceImplement.BX_BooksServiceImp;
import Service.IService.IServiceImplement.UserServiceImp;
import Service.IService.IUserService;

import java.sql.Connection;

public class ServiceFactory {
    public static IUserService getUserServiceInterface(){
        return new UserServiceImp();
    }
    public static IBX_BooksService getBX_BooksServiceInterface(){
        return new BX_BooksServiceImp();
    }
}
