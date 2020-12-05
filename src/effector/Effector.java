package effector;

import Arena.TerrainType;
import unit.Unit;

public abstract class Effector {
    public abstract void effect(Unit unit , TerrainType terrainType) ;
}
