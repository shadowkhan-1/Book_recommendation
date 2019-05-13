package DAO;
import table.user;

import java.util.List;

public interface DAO<K,V> {
    public boolean Create(V vo) throws Exception;
    public V Find(K key) throws Exception;
    public boolean Update(V vo) throws Exception;
    public List<V> FindAll() throws Exception;
}
