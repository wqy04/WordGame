package Round1.main;

import GameMenu.util.GameUtil;

import java.awt.*;

public class GamePeople {

    public Rectangle rect;
    Image bing_s;
    int step = 0;

    private static final String BINGS = "/Round1/ui/img/bings.png";

    public GamePeople(){
        bing_s = GameUtil.LoadBufferedImages(BINGS);
        rect = new Rectangle();
    }

    int x=250,y=450;
    public boolean up=false,down=false,left=false,right=false;

    public void draw(Graphics g){
        g.drawImage(bing_s,x,y,null);
        rect(g);
    }

    public void walk(int num){
        switch (num){
            case 1:
                up=true;
                down=left=right=false;
                y-=50;
                step++;
                break;
            case 2:
                down=true;
                up=left=right=false;
                y+=50;
                step++;
                break;
            case 3:
                left=true;
                down=up=right=false;
                x-=50;
                step++;
                break;
            case 4:
                right=true;
                down=left=up=false;
                x+=50;
                step++;
                break;
        }
    }

    //兵矩形绘制
    public void rect(Graphics g){
        rect.x = this.x;
        rect.y = this.y;
        rect.width = 50;
        rect.height = 50;
        g.setColor(Color.red);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }
}
