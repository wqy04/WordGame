package Round4.main;
import java.awt.*;

import GameMenu.util.GameUtil;
//人物类
public class GamePeople4 {
public Rectangle rect;

 Image pup;
 Image pdown;
 Image pleft;
 Image pright;
 private static final String PEOPLE_UP = "/Round4/ui/img/pup.jpg";
 private static final String PEOPLE_DOWN = "/Round4/ui/img/pdown.jpg";
 private static final String PEOPLE_LEFT = "/Round4/ui/img/pleft.jpg";
 private static final String PEOPLE_RIGHT = "/Round4/ui/img/pright.jpg";
 public GamePeople4() {
	 pup = GameUtil.LoadBufferedImages(PEOPLE_UP);
	 pdown = GameUtil.LoadBufferedImages(PEOPLE_DOWN);
	 pleft = GameUtil.LoadBufferedImages(PEOPLE_LEFT);
	 pright = GameUtil.LoadBufferedImages(PEOPLE_RIGHT);
	 rect = new Rectangle();
 }

 int b = 0;
 int x = 250,y = 250;
 int step = 0;

 /*
  * 绘制人物
  */
 public void draw(Graphics g) {
	 if(b == 0) {
	 g.drawImage(pup, x, y, null);
	 }else {
		 direction(g);
		 rect(g);
	 }
 }

 /*
  * 控制人物的方向
  */
 public boolean up = false, down = false, left = false, right = false;

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

 /*
  * 切换小人的图片
  */
 public void direction (Graphics g) {
	 if(up) {
		 g.drawImage(pup, x, y, null);
	 }else if(down) {
		 g.drawImage(pdown, x, y, null);
	 }else if(left) {
		 g.drawImage(pleft, x, y, null);
	 }else if(right) {
		 g.drawImage(pright, x, y, null);
	 }
 }

 /*
  * 绘制小人的矩形
  */
 public void rect(Graphics g) {
	 rect.x = this.x;
	 rect.y = this.y;
	 rect.width = 50;
	 rect.height = 50;
	 g.setColor(Color.red);
	 g.drawRect(rect.x, rect.y, rect.width, rect.height);
 }
}
