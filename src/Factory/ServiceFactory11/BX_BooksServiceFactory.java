package Factory.ServiceFactory11;

import Service.IService.IBX_BooksService;
import Service.IService.IServiceImplement.BX_BooksServiceImp;

public class BX_BooksServiceFactory implements ServiceFactory{
    @Override
    public IBX_BooksService getService() {
        return new BX_BooksServiceImp();
    }
}
