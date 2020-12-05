package movement;

import Utilitiy.StopWatch;
import unit.Unit;

public class Attack {
    StopWatch stopWatch = new StopWatch() ;
    public Attack(){
        stopWatch = new StopWatch();
    }
    public void Execute(Unit unit,Unit target){
        if(stopWatch.GetElapsedSeconds() >= 1/unit.GetAttackSpeed())
            unit.AttackUnit(target);

    }
}
