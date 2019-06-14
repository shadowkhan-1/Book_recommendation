package Factory.ServiceFactory11;

import Service.IService.IServiceImplement.User_FavoriteServiceImp;
import Service.IService.IUser_FavoriteService;

public class User_FavoriteServiceFactory implements ServiceFactory {
    @Override
    public IUser_FavoriteService getService() {
        return new User_FavoriteServiceImp();
    }
}
