package Arena;

import javafx.geometry.Pos;
import javafx.util.Pair;
import unit.Unit;
import Utilitiy.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    static Grid grid =null;
    public static int CellNum;
    public static int CellSize ;
    static HashMap<Position, Cell> Cells ;
    static HashMap<Position, Pair<Double,ArrayList<Object>>> map ;
    Grid(){ }
    public synchronized static Grid GetGrid(){
        if(grid==null){
            synchronized (Grid.class){
                if(grid==null)
                    grid = new Grid();
            }
        }
        return grid;
    }
    public  void Initialize(int NumOfCells,int Cellsize){
        CellSize = Cellsize;
        CellNum = NumOfCells;
        Cells = new HashMap<Position, Cell>();
        for(int i=0;i<NumOfCells;i++){
            for(int j=0;j<NumOfCells;j++){
                Cells.put(new Position(i,j),new Cell(Cellsize,new Position(i,j)));
            }
        }
    }
    public static TerrainType GetTerrain(Position position){
        return Cells.get(position).GetTerrain();
    }
    public synchronized static boolean HasSpace(Position position,int Size){
        //System.out.println(position);
        if(Cells.get(position).GetSize()>Size){
            return true;
        }
        return false;
    }
    public synchronized static boolean addUnit(Unit unit){
        return Cells.get(unit.getPosition()).AddUnit(unit);
    }
    /*public synchronized static boolean addUnit(Unit unit ,Position position)
    {
        if (map.get(position)==null)
        {
            ArrayList<Object> list = new ArrayList<>() ;
            list.add(unit) ;
            map.put(position,new Pair<>(unit.GetSize().GetValue(),list)) ;
            return true ;
        }
        if (map.get(position).getKey()>=unit.GetSize().GetValue())
        {
            Double size = map.get(position).getKey() - unit.GetSize().GetValue() ;
            ArrayList<Object> list = map.get(position).getValue() ;
            list.add(unit) ;
            map.put(position,new Pair<>(size,list)) ;
            return true ;
        }
        else
            return false ;
    }*/
    public synchronized static void RemoveUnit(Unit unit){
        Cells.get(unit.getPosition()).RemoveUnit(unit);
    }

}
