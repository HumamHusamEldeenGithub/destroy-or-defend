package Arena;

import javafx.collections.ObservableList;
import Utilitiy.Position;
import unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    Position position;
    int size;
    List<Unit> units;
    TerrainType terrainType;
    public Cell(int size,Position position) {
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
        if(unit.GetSize().GetValue()>this.size){
            return false;
        }
        this.size-=unit.GetSize().GetValue();
        units.add(unit);
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
    public int GetSize(){
        return size;
    }

    public TerrainType GetTerrain(){
        return terrainType;
    }
}
