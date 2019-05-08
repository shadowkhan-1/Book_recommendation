package Service;
import java.util.List;
public interface Service<K,V> {
    public boolean insert(V vo) throws Exception;
    public boolean update(V vo) throws Exception;
    public V find(K username) throws Exception;
    public List<V> findall() throws Exception;
}
