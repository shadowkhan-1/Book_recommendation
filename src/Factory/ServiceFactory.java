package Factory;

import Service.IService.IServiceImplement.UserServiceImp;
import Service.IService.IUserService;

import java.sql.Connection;

public class ServiceFactory {
    public static IUserService getUserServiceInterface(){
        return new UserServiceImp();
    }
}
