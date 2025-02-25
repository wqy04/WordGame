package GameMenu.util;

public class PreLocation {
    private int x;
    private int y;
    //1表示玩家
    //0表示可移动物体
    private boolean isPeople;
    public PreLocation(int x , int y, boolean icon){
        this.x = x;
        this.y = y;
        this.isPeople = icon;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPeople() {
        return isPeople;
    }
}
