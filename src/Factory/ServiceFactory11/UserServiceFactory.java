package Factory.ServiceFactory11;

import Service.IService.IServiceImplement.UserServiceImp;
import Service.IService.IUserService;

public class UserServiceFactory implements ServiceFactory {
    @Override
    public IUserService getService() {
        return new UserServiceImp();
    }
}
