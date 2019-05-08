package DAO;
import table.user;

import java.util.List;

public interface DAO<K,V> {
    public boolean Create(V vo) throws Exception;
    public user Find(K username) throws Exception;
    public boolean Update(V vo) throws Exception;
    public List<V> FindAll() throws Exception;
}
