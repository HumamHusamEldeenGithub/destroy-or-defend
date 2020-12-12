package Utilitiy;

import Arena.Grid;
import Arena.TerrainType;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class PathFinder {
    class GridNode{
        public int weight;
        public Position pos;
        public GridNode(){
            pos = new Position(0,0);
        }
    }
    boolean[][] visited = new boolean[10000][10000];
    Comparator cmp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if(((GridNode ) o1).weight > ((GridNode) o2).weight)
                return 1;
            else if(((GridNode) o1).weight == ((GridNode) o2).weight)
                return 0;
            else
                return -1;
        }
    };
    static PathFinder pathFinder = null ;
    PathFinder(){}

    public synchronized static PathFinder GetObj (){
        if (PathFinder.pathFinder==null)
        {
            synchronized (PathFinder.class)
            {
                if (PathFinder.pathFinder==null)
                    pathFinder = new PathFinder() ;
            }
        }
        return PathFinder.pathFinder ;
    }

    SortedSet<GridNode> GridSet = new TreeSet<>(cmp);
    public Position GetPos(Position currentPos,Position Destination,double Range,double Size){
        GridSet.clear();
        // Fill nodes
        for(int i = currentPos.Get_X() - 1; i <= currentPos.Get_X() + 1;i++){
            for(int j = currentPos.Get_Y() - 1;j<= currentPos.Get_Y() + 1;j++){
                if(i>=0 && i<10000 && j>=0 && j<10000 ) {
                    if (Grid.HasSpace(new Position(i, j),(int)Size) && Grid.GetTerrain(new Position(i, j))!= TerrainType.Valley && !visited[j][j] ) {
                        if(i == currentPos.Get_X() && j == currentPos.Get_Y())
                            continue;
                        if(visited[i][j])
                            continue;
                        GridNode node = new GridNode();
                        node.weight = Math.abs(Destination.Get_X() - i) +Math.abs( Destination.Get_Y() - j);
                        if(Grid.GetTerrain(new Position(i, j))==TerrainType.River)
                            node.weight+=2;
                        node.pos.Set_X(i);
                        node.pos.Set_Y(j);
                        GridSet.add(node);
                    }
                }
            }
        }
        // Check best solution
        for(GridNode node : GridSet){

            Position newCur = node.pos;
            if(Math.abs(newCur.Get_X() - Destination.Get_X()) < Range && Math.abs(newCur.Get_Y() - Destination.Get_Y()) < Range){
                if(node == GridSet.last()){
                    visited[node.pos.Get_X()][node.pos.Get_Y()] = true;
                    return currentPos;
                }
                continue;
            }
            //DFS
            for(int i = 0;i < Grid.CellNum / 40;i++){
                newCur = Think(newCur,Destination,Range,Size);
                if(newCur==null){
                    break;
                }
            }
            if(newCur!=null) {
                visited[node.pos.Get_X()][node.pos.Get_Y()] = true;
                return node.pos;
            }
            if(node.equals(GridSet.last())){
                visited[node.pos.Get_X()][node.pos.Get_Y()] = true;
                return node.pos;
            }
        }
        return null;
    }

    Position Think(Position currentPos,Position Destination,double Range,double Size){
        if(Math.abs(currentPos.Get_X() - Destination.Get_X()) <=Range && Math.abs(currentPos.Get_Y() - Destination.Get_Y()) <= Range){
            return currentPos;
        }
        SortedSet<GridNode> Nodes = new TreeSet<>(cmp);
        for(int i = currentPos.Get_X() - 1; i <= currentPos.Get_X() + 1;i++){
            for(int j = currentPos.Get_Y() - 1;j<= currentPos.Get_Y()+1;j++){
                if(i>=0 && i<Grid.CellNum && j>=0 && j<Grid.CellNum ) {
                    if (Grid.HasSpace(new Position(i, j),(int)Size) && Grid.GetTerrain(new Position(i, j))!=TerrainType.Valley && !visited[j][j]) {
                        GridNode node = new GridNode();
                        node.weight = Math.abs(Destination.Get_X() - i + Destination.Get_Y() - j);
                        if(Grid.GetTerrain(new Position(i, j))==TerrainType.River)
                            node.weight+=2;
                        node.pos.Set_X(i);
                        node.pos.Set_Y(j);
                        Nodes.add(node);
                    }
                }
            }
        }
        Position finalPosition = null;
        for(GridNode node : Nodes){
            if(Destination.Get_X()<currentPos.Get_X()){
                if(node.pos.Get_X()<currentPos.Get_X())
                    finalPosition = currentPos;
                if(Grid.GetTerrain(currentPos)==TerrainType.Bridge)
                    return finalPosition;
            }
            else if(Destination.Get_X()>currentPos.Get_X()){
                if(node.pos.Get_X()>currentPos.Get_X())
                    finalPosition = currentPos;
                if(Grid.GetTerrain(currentPos)==TerrainType.Bridge)
                    return finalPosition;
            }
            else if(Destination.Get_Y()<currentPos.Get_Y()){
                if(node.pos.Get_Y()<currentPos.Get_Y())
                    finalPosition = currentPos;
                if(Grid.GetTerrain(currentPos)==TerrainType.Bridge)
                    return finalPosition;
            }
            else if(Destination.Get_Y()>currentPos.Get_Y()){
                if(node.pos.Get_Y()>currentPos.Get_Y())
                    finalPosition = currentPos;
                if(Grid.GetTerrain(currentPos)==TerrainType.Bridge)
                    return finalPosition;
            }
        }
        return finalPosition;
    }
}
