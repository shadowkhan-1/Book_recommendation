package Factory.ServiceFactory11;

import Service.Service;

public abstract class AbstractServiceFactory {
    public abstract Service getService(String name);
}
