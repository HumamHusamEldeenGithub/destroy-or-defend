package Utilitiy;

import Arena.Grid;
import Arena.TerrainType;

import java.awt.*;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TerrainGenerator {

    class Point {
        public double x;
        public double y;
        public Point(double x,double y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return Double.toString(x) + " " + Double.toString(y);
        }
    }

    public void GenerateTerrain() {
        int CellNum = Grid.CellNum;
        Position RightDownCorner = new Position(CellNum/2 - 1,CellNum/2 - 1);
        //First Section
        int X1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        int X2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        int Y1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        int Y2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        List<Point> curLine = GetLine(new Position(X1,Y1),new Position(X2,Y2));
        boolean choice = new Random().nextBoolean();
        MakeChoice(choice,curLine);
        System.out.println("done here");

        //Second Section
        X1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        X2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        Y1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        Y2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        choice = new Random().nextBoolean();
        curLine = GetLine(new Position(X1,Y1),new Position(X2,Y2));
        MakeChoice(choice,curLine);
        System.out.println("done here");

        //Third Section
        X1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        X2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X()));
        Y1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        Y2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        choice = new Random().nextBoolean();
        curLine = GetLine(new Position(X1,Y1),new Position(X2,Y2));
        MakeChoice(choice,curLine);
        System.out.println("done here");

        //Fourth Section
        X1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        X2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        Y1 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        Y2 = Math.abs(new Random().nextInt(RightDownCorner.Get_X())) + CellNum/2;
        choice = new Random().nextBoolean();
        curLine = GetLine(new Position(X1,Y1),new Position(X2,Y2));
        MakeChoice(choice,curLine);
        System.out.println("done here");
    }

    private void MakeChoice(boolean choice,List<Point> points){
        if(choice){
            GenerateChunk(points,TerrainType.River);
        }
        else
            GenerateChunk(points,TerrainType.Valley);
    }

    private void GenerateChunk(List<Point> points,TerrainType terrainType) {
        boolean bridgeMade = false;
        for(Point point : points){
            int bridgeChance = new Random().nextInt(50);
            boolean makeBridge = bridgeChance>40 && point!= points.get(0);
            for(int i=(int)point.x-4;i<point.x+4;i++){
                for (int j=(int)point.y-4;j<point.y+4;j++){
                    if(makeBridge){
                        if(bridgeMade){
                            if(Grid.GetTerrain(new Position(i,j))!=TerrainType.Bridge)
                                Grid.SetTerrain(new Position(i,j), terrainType);
                        }
                        else{
                            Grid.SetTerrain(new Position(i,j), TerrainType.Bridge);
                        }
                    }
                    else{
                        if(Grid.GetTerrain(new Position(i,j))!=TerrainType.Bridge)
                            Grid.SetTerrain(new Position(i,j), terrainType);
                    }
                }
            }
            if(makeBridge)
                bridgeMade = true;
        }
    }

    private List<Point> GetLine(Position first, Position second){
        Point p1 = new Point(first.Get_X(),first.Get_Y());

        Point p2 = new Point(second.Get_X(),second.Get_Y());
        List<Point> Points = new ArrayList<Point>();
        double N = diagonal_distance(p1 , p2);
        for (double step = 0; step <= N; step++) {
            double t = N == 0? 0.0 : step / N;
            Points.add(round_point(lerp_point(p1, p2, t)));
        }
        List<Point> additionalPoints = new ArrayList<Point>();
        for(int i=1;i<Points.size();i++){
            if(Math.abs(Points.get(i).y - Points.get(i-1).y) > 0 && Math.abs(Points.get(i).x - Points.get(i-1).x) > 0){
                additionalPoints.add(new Point(Points.get(i-1).x,Points.get(i).y));
            }
        }
        Points.addAll(additionalPoints);
        /*
        for(Point point : Points){
            System.out.println(point);
        }
         */
        return Points;
    }

    private Point round_point(Point p) {
        return new Point(Math.round(p.x),Math.round(p.y));
    }

    private Point lerp_point(Point p0,Point p1,double t) {
        return new Point(lerp(p0.x, p1.x, t),
                lerp(p0.y, p1.y, t));
    }

    private double lerp(double start, double end,double t) {
        return start + t * (end-start);
    }
    private double diagonal_distance(Point p0,Point p1) {
        double dx = p1.x - p0.x, dy = p1.y - p0.y;
        return Math.max(Math.abs(dx), Math.abs(dy));
    }
}
