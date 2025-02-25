package Round1.main;

import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.SoundMusicPlayer;
import GameMenu.util.GameUtil;

import java.awt.*;
import java.util.ArrayList;


public class GameBox {

    public Rectangle rect;
    private AudioController audioController;
    private final MainFrame mainFrame;
    private final HelpFrame helpFrame;
    private SoundMusicPlayer playHuo, playbing, playcrush;

    public ArrayList<Rectangle> rects;

    boolean chenum = true;

    //0是空
    //1是河
    //2是冰
    //3是火
    //5是树
    int[][] box = {
            {5,5,0,0,0,0,0,0,0,5,0,5,0,5,0,5,0,0,5},
            {0,5,0,5,0,0,5,0,5,0,0,5,0,0,5,0,0,5,0},
            {0,0,5,0,0,0,0,0,0,0,3,0,5,0,0,5,0,0,0},
            {5,0,0,0,5,0,0,5,0,0,0,0,0,5,0,0,5,0,5},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {5,5,0,0,5,0,0,0,5,0,5,0,0,0,0,0,5,0,0},
            {0,0,0,2,0,5,0,0,0,0,0,5,0,0,5,0,0,5,0},
            {5,0,5,0,0,0,0,5,0,0,0,0,0,5,0,0,5,0,0},
            {0,0,0,5,0,5,0,0,5,0,0,5,0,5,0,0,0,0,5},
    };

    int[][] boxs = {
            {5,5,0,0,0,0,0,0,0,5,0,5,0,5,0,5,0,0,5},
            {0,5,0,5,0,0,5,0,5,0,0,5,0,0,5,0,0,5,0},
            {0,0,5,0,0,0,0,0,0,0,3,0,5,0,0,5,0,0,0},
            {5,0,0,0,5,0,0,5,0,0,0,0,0,5,0,0,5,0,5},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
            {5,5,0,0,5,0,0,0,5,0,5,0,0,0,0,0,5,0,0},
            {0,0,0,0,0,5,0,0,0,0,0,5,0,0,5,0,0,5,0},
            {5,0,5,0,0,0,0,5,0,0,0,0,0,5,0,0,5,0,0},
            {0,0,0,5,0,5,0,0,5,0,0,5,0,5,0,0,0,0,5},
    };

    int[][] box1 = {
            {5,5,0,0,0,0,0,0,0,5,0,5,0,5,0,5,0,0,5},
            {0,5,0,5,0,0,5,0,5,0,0,5,0,0,5,0,0,5,0},
            {0,0,5,0,0,0,0,0,0,0,0,0,5,0,0,5,0,0,0},
            {5,0,0,0,5,0,0,5,0,0,0,0,0,5,0,0,5,0,5},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {5,5,0,0,5,0,0,0,5,0,5,0,0,0,0,0,5,0,0},
            {0,0,0,0,0,5,0,0,0,0,0,5,0,0,5,0,0,5,0},
            {5,0,5,0,0,0,0,5,0,0,0,0,0,5,0,0,5,0,0},
            {0,0,0,5,0,5,0,0,5,0,0,5,0,5,0,0,0,0,5},
    };

    private Image he;
    private Image bing;
    private Image huo;

    private Image ch;

    private Image cao;
    Rectangle removeRect = new Rectangle();

    private static final String HE = "/Round1/ui/img/he.png";
    private static final String HUO = "/Round1/ui/img/huo.png";
    private static final String BING = "/Round1/ui/img/bing.png";
    private static final String CH = "/Round1/ui/img/ch.png";

    private static final String SHU = "/Round1/ui/img/cao.png";
    private boolean isFirstDrawComplete = false;

    public GameBox(MainFrame mainFrame, HelpFrame helpFrame){
        this.mainFrame = mainFrame;
        this.helpFrame = helpFrame;
        this.audioController = mainFrame.getAudioController();

        he = GameUtil.LoadBufferedImages(HE);
        huo = GameUtil.LoadBufferedImages(HUO);
        bing = GameUtil.LoadBufferedImages(BING);
        ch = GameUtil.LoadBufferedImages(CH);
        cao = GameUtil.LoadBufferedImages(SHU);


        exit = false;
        rect = new Rectangle();
        rects = new ArrayList<>();

        // 音频资源的初始化
        playHuo = new SoundMusicPlayer("/Round1/ui/music/huo.wav");
        playbing = new SoundMusicPlayer("/Round1/ui/music/jiebing.wav");
        playcrush = new SoundMusicPlayer("/Round1/ui/music/crush.wav");
        audioController.addSound(playHuo);
        audioController.addSound(playcrush);
        audioController.addSound(playbing);
    }

    //火与冰的位置
    int x=0,y=0;
    boolean controlhe = true;
    //private GameChange gameChange;
    public void draw(Graphics g,GamePeople gamePeople){
        if(chenum){
            che();
            chenum=false;
        }
        for(int j=0;j<box.length;j++){
            for(int i=0;i<box[0].length;i++){
                switch (box[j][i]){
                    case 0:
                        x=(i+1)*50;
                        y=50+j*50;
                        break;
                    case 1:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(he,x,y,null);
                        save(x,y,g);
                        break;
                    //用来画冰
                    case 2:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(j-1<0){
                                    j++;
                                    gamePeople.y+=50;
                                }else {
                                    if(box[j-1][i]!=1){
                                        box[j][i] = 0;
                                        box[j-1][i] = 2;
                                    }else if(box[j-1][i]==5) {
                                        box[j][i] = 2;
                                        gamePeople.y-=50;
                                    } else{
                                        box[j][i] = 0;
                                        box[j-1][i] = 2;
                                        x=i*50;
                                        y=50+j*50;
                                        controlhe = true;
                                        playbing.play();
                                        playbing.stop();
                                        playHuo.play();
                                        playHuo.stop();
                                        box = boxs;
                                        //清除河流矩形
                                        for(int p=0;p<rects.size();p++){
                                            if(rects.get(p).y == 250||rects.get(p).y == 300){
                                                rects.remove(p);
                                                p--;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }else if(gamePeople.down) {
                                if(j+1>=box.length){
                                    j--;
                                    gamePeople.y-=50;
                                }
                                if(box[j+1][i]==5){
                                    box[j][i] = 2;
                                    gamePeople.y-=50;
                                }else{
                                    box[j][i] = 0;
                                    box[j+1][i] = 2;
                                }

                            }else if(gamePeople.right){
                                if(i+1>=box[0].length){
                                    i--;
                                    gamePeople.x-=50;
                                }
                                if(box[j][i+1]==5){
                                    box[j][i] = 2;
                                    gamePeople.x-=50;
                                }else{
                                    box[j][i] = 0;
                                    box[j][i+1]=2;
                                }
                            }else if(gamePeople.left){
                                if(i-1<0){
                                    i++;
                                    gamePeople.x+=50;
                                }
                                if(box[j][i-1]==5){
                                    box[j][i]=2;
                                    gamePeople.x+=50;
                                }else{
                                    box[j][i] = 0;
                                    box[j][i-1] = 2;
                                }

                            }
                        }
                        g.drawImage(bing,x,y,null);
                        break;
                    //用来画火
                    case 3:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(huo,x,y,null);
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(j-1<0){
                                    j++;
                                    gamePeople.y+=50;
                                }
                                if(box[j-1][i] == 5){
                                    removeRect.x=x;
                                    removeRect.y=y-50;
                                    removeRect.width = 50;
                                    removeRect.height = 50;
                                    rects.remove(removeRect);
                                }
                                box[j][i]=0;
                                box[j-1][i]=3;
                            }else if(gamePeople.down) {
                                if(j+1>box.length){
                                    j--;
                                }
                                if(box[j+1][i] == 5){
                                    box[j][i] = 0;
                                    box[j+1][i] = 3;
                                    removeRect.x=x;
                                    removeRect.y=y+50;
                                    removeRect.width = 50;
                                    removeRect.height = 50;
                                    rects.remove(removeRect);
                                }
                                if(box[j+1][i]==0){
                                    box[j][i] = 0;
                                    box[j+1][i] = 3;
                                }else if(box[j+1][i] == 4){
                                    box[j][i] = 0;
                                    box[j+1][i] = 3;
                                    x=(i+1)*50;
                                    y=50+j*50;
                                    box = box1;
                                }

                            }else if(gamePeople.right){
                                if(i+1>=box[0].length){
                                    i--;
                                    gamePeople.x = gamePeople.x-50;
                                }
                                if(box[j][i+1] == 5){
                                    removeRect.x=x+50;
                                    removeRect.y=y;
                                    removeRect.width = 50;
                                    removeRect.height = 50;
                                    rects.remove(removeRect);
                                }
                                box[j][i] = 0;
                                box[j][i+1]=3;
                            }else if(gamePeople.left){
                                if(i-1<0){
                                    i++;
                                    gamePeople.x=gamePeople.x+50;
                                }if(box[j][i-1] == 5){
                                    removeRect.x=x-50;
                                    removeRect.y=y;
                                    removeRect.width = 50;
                                    removeRect.height = 50;
                                    rects.remove(removeRect);
                                }
                                box[j][i] = 0;
                                box[j][i-1] = 3;
                            }
                        }
                        break;
                    case 4:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(ch,x,y,null);
                        break;
                    case 5:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(cao,x,y,null);
                        save(x,y,g);
                }
            }
        }
        coll(gamePeople);
        colls(gamePeople);
        ifWin();
    }


    //绘制墙体矩形
    public void save(int x,int y,Graphics g){
        if(filesize>0){
            GameBox gameBox = new GameBox(mainFrame, helpFrame);
            gameBox.rect.x = x;
            gameBox.rect.y = y;
            gameBox.rect.width = 50;
            gameBox.rect.height = 50;
            rects.add(gameBox.rect);
            filesize--;
        }
        g.setColor(Color.red);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }
    int filesize = 0;
    public void che(){
        for(int i=0;i<box.length;i++){
            for(int j=0;j<box[0].length;j++){
                if(box[i][j]==1||box[i][j]==5){
                    filesize++;
                }
            }
        }
    }
    //判断碰撞
    public void coll(GamePeople gamePeople){
        for(int i=0;i<rects.size();i++){
            Rectangle rectangle = rects.get(i);
            //判断矩形是否相交
            if(gamePeople.rect.intersects(rectangle)){
                playcrush.play();
                playcrush.stop();
                //System.out.println("撞上了");
                if(gamePeople.up){
                    gamePeople.y+=50;
                }else if(gamePeople.down){
                    gamePeople.y-=50;
                }else if(gamePeople.right){
                    gamePeople.x-=50;
                }else if(gamePeople.left){
                    gamePeople.x+=50;
                }
            }
        }
    }

    //判断是否与提示相撞
    public void colls(GamePeople gamePeople) {
        //判断矩形是否相交
        if (gamePeople.y > 500 || gamePeople.y < 50 || gamePeople.x < 50 || gamePeople.x > 950) {
            if (gamePeople.down) {
                gamePeople.y -= 50;
            } else if (gamePeople.up) {
                gamePeople.y += 50;
            } else if (gamePeople.left) {
                gamePeople.x += 50;
            } else if (gamePeople.right) {
                gamePeople.x -= 50;
            }
        }
    }
    public boolean exit = false;

    public void ifWin() {
        int i = 0, j = 0;
        boolean flag = true;
        for (j = 0; j < box.length; j++) {
            for (i = 0; i < box[0].length; i++) {
                if (box[j][i] == 3) {
                    flag = false;
                }
            }
        }
        if (flag) {
            //此处弹出胜利界面
            playHuo.pause();
            playHuo.stop();
            playbing.stop();
            System.out.println("赢啦！");
            exit = true;
        }
    }
}
