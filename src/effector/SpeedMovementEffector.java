package effector;

import Arena.TerrainType;
import unit.Unit;

public class SpeedMovementEffector extends Effector{
    static SpeedMovementEffector speedMovementEffector = null ;
    SpeedMovementEffector(){}

    public synchronized static SpeedMovementEffector getObj()
    {
        if (SpeedMovementEffector.speedMovementEffector==null)
        {
            synchronized (SpeedMovementEffector.class)
            {
                if (SpeedMovementEffector.speedMovementEffector==null)
                    SpeedMovementEffector.speedMovementEffector=new SpeedMovementEffector() ;
            }
        }
        return SpeedMovementEffector.speedMovementEffector ;
    }

    @Override
    public synchronized void effect(Unit unit, TerrainType terrainType) {
        if (terrainType==TerrainType.River)
            unit.SetSpeedMovementEffectorValue(3.5);
        else if (terrainType==TerrainType.Flat)
            unit.SetSpeedMovementEffectorValue(1);

    }

}
