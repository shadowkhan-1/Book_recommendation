package Factory.ServiceFactory11;

import Factory.ServiceFactory11.AbstractServiceFactory;
import Factory.ServiceFactory11.BX_BooksServiceFactory;
import Factory.ServiceFactory11.UserServiceFactory;
import Factory.ServiceFactory11.User_FavoriteServiceFactory;
import Service.Service;

public class Servicefactory11 extends AbstractServiceFactory {
    @Override
    public Service getService(String name) {
        if("BX_Books_Service".equalsIgnoreCase(name)){
                return new BX_BooksServiceFactory().getService();
        }
        else if("UserService".equalsIgnoreCase(name)){
                return new UserServiceFactory().getService();
        }
        else if("User_Favorite_Service".equalsIgnoreCase(name)){
                return new User_FavoriteServiceFactory().getService();
        }
        else {
            System.out.println("参数输入错误或者没有这个service类！");
            return null;
        }
    }
}
