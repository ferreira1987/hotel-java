package dao;

public interface Dao {
 
    public int incluir(Object o);
    
    public void update(Object o);
    
    public boolean delete(Object o);
    
    public Object select(int i);
    
}
