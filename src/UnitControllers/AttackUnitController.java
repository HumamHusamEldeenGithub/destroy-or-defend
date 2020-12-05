package UnitControllers;

import Utilitiy.StopWatch;
import Utilitiy.StopWatchPool;
import unit.Unit;

public class AttackUnitController {
    StopWatch stopWatch = new StopWatch() ;
    public AttackUnitController(){
        stopWatch = new StopWatch();
        StopWatchPool.GetObj().AddObj(stopWatch);
    }
    public void Execute(Unit unit,Unit target){
        if(stopWatch.GetElapsedSeconds() >= 1/unit.GetAttackSpeed().GetValue()) {
            unit.AttackUnit(target);
            stopWatch.Reset();
        }
        stopWatch.Start();
    }
}
