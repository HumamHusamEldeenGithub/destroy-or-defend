package Arena;

import effector.SpeedMovementEffector;
import unit.Unit;
import Utilitiy.Position;
import unit.UnitType;

import java.util.HashMap;

public class Grid {
    static Grid grid =null;
    public static int CellNum;
    public static int CellSize ;
    static HashMap<Position, GridCell> Cells ;
    static Unit mainBase ;
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
        Cells = new HashMap<Position, GridCell>();
        for(int i=0;i<NumOfCells;i++){
            for(int j=0;j<NumOfCells;j++){
                Cells.put(new Position(i,j),new GridCell(Cellsize,new Position(i,j)));
            }
        }
    }
    public synchronized static TerrainType GetTerrain(Position position){
        if(Cells.containsKey(position))
            return Cells.get(position).GetTerrain();
        return null;
    }
    public synchronized static void SetTerrain(Position position,TerrainType terrainType){
        if(Cells.containsKey(position))
            Cells.get(position).SetTerrain(terrainType);
    }
    public synchronized static boolean HasSpace(Position position,int Size){
        //System.out.println(position);
        if(Cells.get(position).GetSize()>Size){
            return true;
        }
        return false;
    }
    public static void Print(){
        for(int i=0;i<CellNum;i++){
            for(int j=0;j<CellNum;j++){
                TerrainType type = Cells.get(new Position(i,j)).GetTerrain();
                if(type==TerrainType.River){
                    System.out.print("r ");
                }
                else if(type==TerrainType.Valley){
                    System.out.print("v ");
                }
                else if(type==TerrainType.Bridge){
                    System.out.print("B ");
                }
                else{
                    System.out.print(". ");
                }

            }
            System.out.println();
        }
    }
    public synchronized static boolean addUnit(Unit unit){
        if(unit.GetType() == UnitType.MainBase){
            Grid.mainBase = unit;
        }
        if (Cells.get(unit.GetPosition()).AddUnit(unit))
        {
            SpeedMovementEffector.getObj().effect(unit,Cells.get(unit.GetPosition()).terrainType);
            return true ;
        }
        return false ;
    }

    public synchronized static boolean CheckCell(Position pos,Unit unit){
        return Cells.get(pos).CheckAvailability(pos,unit);
    }

    public synchronized static void RemoveUnit(Unit unit){
        Cells.get(unit.GetPosition()).RemoveUnit(unit);
    }

    public static synchronized Position getBasePosition(){
        return mainBase.GetPosition() ;
    }

    public static synchronized GridCell GetCell(Position pos){
        if(Cells.containsKey(pos))
            return Cells.get(pos);
        return null;
    }
    public static GridCell[][] GetCut(Position position){
        if(position.Get_X()<0){
            position.Set_X(0);
        }
        if(position.Get_Y()<0){
            position.Set_Y(0);
        }
        if(position.Get_X()>= CellNum){
            position.Set_X(CellNum - 10);
        }
        if(position.Get_Y()>= CellNum){
            position.Set_Y(CellNum - 10);
        }
        GridCell[][] gridCells = new GridCell[10][10];
        for(int i=position.Get_X(),newI=0;i<position.Get_X()+10 && newI < 10;i++,newI++){
            for(int j=position.Get_Y(),newJ = 0;j<position.Get_Y()+10 && newJ < 10;j++,newJ++){
                gridCells[newI][newJ] = Cells.get(new Position(i,j));
            }
        }
        return gridCells;
    }
}
