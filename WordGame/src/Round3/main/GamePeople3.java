package Round3.main;

import GameMenu.util.GameUtil;

import java.awt.*;

public class GamePeople3 {

    public Rectangle rect;
    Image people_up;
    Image people_down;
    Image people_left;
    Image people_right;

    private static final String PEOPLE_UP = "/Round3/ui/img/people_up.jpg";
    private static final String PEOPLE_DOWN = "/Round3/ui/img/people_down.JPG";
    private static final String PEOPLE_LEFT = "/Round3/ui/img/people_left.jpg";
    private static final String PEOPLE_RIGHT = "/Round3/ui/img/people_right.jpg";

    public GamePeople3() {
        people_up = GameUtil.LoadBufferedImages(PEOPLE_UP);
        people_down = GameUtil.LoadBufferedImages(PEOPLE_DOWN);
        people_left = GameUtil.LoadBufferedImages(PEOPLE_LEFT);
        people_right = GameUtil.LoadBufferedImages(PEOPLE_RIGHT);
        rect = new Rectangle();
    }

    int x = 100, y = 150;

    //人转向的变换
    int b = 0;

    //计步
    int step = 0;

    public boolean up = false, down = false, left = false, right = false;

    public void draw(Graphics g) {
        if (b == 0) {
            g.drawImage(people_down, x, y, null);
        } else {
            direction(g);
        }
        rect(g);
    }

    public void walk(int num) {
        switch (num) {
            case 1:
                up = true;
                down = left = right = false;
                y -= 50;
                b = 1;
                step++;
                break;
            case 2:
                down = true;
                up = left = right = false;
                y += 50;
                b = 1;
                step++;
                break;
            case 3:
                left = true;
                down = up = right = false;
                x -= 50;
                b = 1;
                step++;
                break;
            case 4:
                right = true;
                down = left = up = false;
                x += 50;
                b = 1;
                step++;
                break;
        }
    }

    //人的矩形绘制
    public void rect(Graphics g) {
        rect.x = this.x;
        rect.y = this.y;
        rect.width = 50;
        rect.height = 50;
        g.setColor(Color.red);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    //切换小人图片
    public void direction(Graphics g) {
        if (up) {
            g.drawImage(people_up, x, y, null);
        } else if (down) {
            g.drawImage(people_down, x, y, null);
        } else if (left) {
            g.drawImage(people_left, x, y, null);
        } else if (right) {
            g.drawImage(people_right, x, y, null);
        }

    }
}
