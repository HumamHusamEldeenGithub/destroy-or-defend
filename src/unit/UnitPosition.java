package unit;

public class UnitPosition {
    int centerX ;
    int centerY ;
    int radius ;
    public UnitPosition(int x , int y ,int radius) {
        this.centerX = x ;
        this.centerY =y ;
        this.radius = radius;
    }
    public boolean squareIsOccupied(){
        return false;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }
}
