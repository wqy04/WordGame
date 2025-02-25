package GameMenu.util;

import java.awt.*;

//画黑色背景的类
public class GameBlackBoard {
    int x=0,y=50;
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1030,640);
    }
}
