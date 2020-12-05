package Utilitiy;

import java.util.List;

public abstract class Pool {
    static List<Object> Objects;
    protected static Pool pool;
    public abstract void UpdateObjs();
    public void AddObj(Object obj){
        Objects.add(obj);
    }
}
