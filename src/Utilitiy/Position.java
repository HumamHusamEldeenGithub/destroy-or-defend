package Utilitiy;

public class Position {
    int centerX;
    int centerY;

    public Position(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    public int Get_X() {
        return centerX;
    }

    public int Get_Y() {
        return centerY;
    }

    public void Set_X(int X) {
        centerX = X;
    }

    public void Set_Y(int Y) {
        centerX = Y;
    }

    public String toString() {
        return "X = " + this.centerX + "  Y = " + this.centerY;
    }

    @Override
    public boolean equals(Object o) {
        return this.centerX == ((Position) o).Get_X() && this.centerY == ((Position) o).Get_Y();

    }
}
