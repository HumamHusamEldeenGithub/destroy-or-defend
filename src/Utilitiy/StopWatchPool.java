package Utilitiy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StopWatchPool extends Pool{
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


