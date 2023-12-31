package Arena;

import javafx.collections.ObservableList;
import Utilitiy.Position;
import unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class GridCell {
    Position position;
    int size;
    List<Unit> units;
    TerrainType terrainType;
    public GridCell(int size, Position position) {
        this.size = size;
        this.position = position;
        terrainType = TerrainType.Flat;
        units = new ArrayList<Unit>();
    }
    public List<Unit> GetUnits(){
        return  units;
    }
    public void UpdateTerrain(TerrainType terrainType){
        this.terrainType = terrainType;
    }
    public boolean AddUnit(Unit unit){
        if(unit.GetSize().GetValue()>this.size || terrainType == TerrainType.Valley){
            return false;
        }
        this.size-=unit.GetSize().GetValue();
        units.add(unit);
        return true;
    }

    public boolean CheckAvailability(Position pos,Unit unit){
        if(unit.GetSize().GetValue()>this.size || terrainType == TerrainType.Valley){
            return false;
        }
        return true;
    }
    public void RemoveUnit(Unit unit){
        try{
            units.remove(unit);
            size+=unit.GetSize().GetValue();
        }
        catch (Exception e){
            System.err.println("Error removing unit");
        }
    }
    public void SetSize(int size){this.size =size; }

    public int GetSize(){ return this.size; }

    public TerrainType GetTerrain(){
        return terrainType;
    }

    public void SetTerrain(TerrainType terrainType){
        this.terrainType = terrainType;
    }
}
