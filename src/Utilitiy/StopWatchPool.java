package Utilitiy;

import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StopWatchPool extends Pool{
    public synchronized static StopWatchPool GetObj (){
        if (Pool.pool==null)
        {
            synchronized (Pool.class)
            {
                if (Pool.pool==null)
                    pool = new StopWatchPool() ;
            }
        }
        return (StopWatchPool) Pool.pool ;
    }
    public StopWatchPool(){
        this.Objects = new ArrayList<Object>();
    }

    @Override
    public void UpdateObjs() {
        for(Object obj : Objects){
            ((StopWatch)obj).Update();
        }
    }
}


