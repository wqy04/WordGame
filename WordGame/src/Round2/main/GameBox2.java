package Round2.main;

import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.SoundMusicPlayer;
import GameMenu.util.GameUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GameBox2 {

    public Rectangle rect;
    private MainFrame mainFrame;
    private HelpFrame helpFrame;
    public ArrayList<Rectangle> rects;
    private AudioController audioController;
    boolean chenum = true;
    public boolean pass = true;
    

    /**
     * 0代表空
     * 1代表树
     * 2代表草
     * 3代表鸟
     * 4代表刀
     * 5代表弓
     * 6代表鹿
     * 7代表羽毛
     * 8代表枝
     * 9代表箭
     * 10代表矢
     */
    int[][] box = {
    		{0,0,2,0,0,1,1,1,0,1,2,0,1,0,1,1,0,1,1},
            {0,1,1,0,0,0,0,0,0,0,2,1,0,0,0,0,0,2,1},
            {2,0,0,0,5,0,0,1,0,3,0,0,0,1,0,0,0,0,0},
            {0,2,0,0,0,0,0,0,0,0,0,4,0,1,0,0,2,1,0},
            {1,6,0,0,0,0,4,0,0,0,1,0,0,1,0,0,0,0,0},
            {0,0,0,4,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0},
            {2,0,0,1,0,0,0,0,1,2,0,4,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,2,0,0,0,0,5,0,0,1,0},
            {0,0,1,0,0,0,5,0,0,0,3,0,2,0,0,0,0,0,0},
            {1,0,0,0,2,0,0,1,0,0,0,0,2,4,0,0,0,2,0},
            {1,0,0,1,0,0,1,0,1,2,0,0,1,0,0,0,2,1,1}
    };

    private Image shu, cao, niao, dao, gong, lu, yun, zhi, jian, shi, sheng, tu;
    private SoundMusicPlayer playerShu, playerCrush, playerDaoDi, playerSheJian, playerNiaoMing, playerLuMing;

    private static final String SHU = "/Round2/ui/img/shu.png";
    private static final String CAO = "/Round2/ui/img/cao.png";
    private static final String NIAO = "/Round2/ui/img/niao.png";
    private static final String DAO = "/Round2/ui/img/dao.png";
    private static final String GONG = "/Round2/ui/img/gong.png";
    private static final String LU = "/Round2/ui/img/lu.png";
    private static final String YUN = "/Round2/ui/img/yu.png";
    private static final String ZHI = "/Round2/ui/img/zhi.png";
    private static final String JIAN = "/Round2/ui/img/jian.png";
    private static final String SHI = "/Round2/ui/img/shi.png";
    
    
    

    public GameBox2(MainFrame mainFrame, HelpFrame helpFrame){
        this.mainFrame = mainFrame;
        this.helpFrame = helpFrame;
        this.audioController = mainFrame.getAudioController();

        shu = GameUtil.LoadBufferedImages(SHU);
        cao = GameUtil.LoadBufferedImages(CAO);
        niao = GameUtil.LoadBufferedImages(NIAO);
        dao = GameUtil.LoadBufferedImages(DAO);
        gong = GameUtil.LoadBufferedImages(GONG);
        lu = GameUtil.LoadBufferedImages(LU);
        yun = GameUtil.LoadBufferedImages(YUN);
        zhi = GameUtil.LoadBufferedImages(ZHI);
        jian = GameUtil.LoadBufferedImages(JIAN);
        shi =  GameUtil.LoadBufferedImages(SHI);

        // 音频资源初始化
        playerShu = new SoundMusicPlayer("/Round2/ui/music/-砍柴伐木干活.wav");
        playerCrush = new SoundMusicPlayer("/Round2/ui/music/crush.wav");
        playerDaoDi = new SoundMusicPlayer("/Round2/ui/music/倒地.wav");
        playerSheJian = new SoundMusicPlayer("/Round2/ui/music/射箭.wav");
        playerNiaoMing = new SoundMusicPlayer("/Round2/ui/music/鸟鸣.wav");
        playerLuMing = new SoundMusicPlayer("/Round2/ui/music/鹿鸣.wav");
        audioController.addSound(playerShu);
        audioController.addSound(playerCrush);
        audioController.addSound(playerDaoDi);
        audioController.addSound(playerSheJian);
        audioController.addSound(playerNiaoMing);
        audioController.addSound(playerLuMing);

        rect = new Rectangle();
        rects = new ArrayList<>();
    }

    
    int x=0,y=0;
    
    //private GameChange gameChange;
    public void draw(Graphics g, GamePeople2 gamePeople){
    	 if (chenum) {
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
                    //碰到树
                    case 1:
                    	
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(shu,x,y,null);
                        save(x,y,g);
                        
                        break;
                    //碰到草
                    case 2:
                    	
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(cao,x,y,null);
                        save(x,y,g);
                        
                        break;
                    //碰到鸟变成羽毛7
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 3:
                    	x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(niao,x,y,null);
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                gamePeople.y+=50;
                                //对于鸟碰撞的情况
                                if (box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){
                                    box[j][i]=0;
                                    box[j-1][i]=7;
                                }else if (box[j-1][i]==4) {
                                    box[j][i]=7;
                                    box[j-1][i]= 4;
                                }else if (box[j-1][i]==5) {
                                    box[j][i]=7;
                                    box[j-1][i]= 5;
                                }else if (box[j-1][i]==7) {
                                    box[j][i]=7;
                                    box[j-1][i]= 7;
                                }else if (box[j-1][i]==8) {
                                    box[j][i]=7;
                                    box[j-1][i]= 8;
                                }else if (box[j-1][i]==9) {
                                    box[j][i]=7;
                                    box[j-1][i]= 9;
                                }else if (box[j-1][i]==10) {
                                    box[j][i]=7;
                                    box[j-1][i]= 10;
                                }
                                playerNiaoMing.play();
                                playerNiaoMing.stop();
                            }else if(gamePeople.down) {
                                gamePeople.y-=50;
                                if (box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10){
                                    box[j][i]=0;
                                    box[j+1][i]=7;
                                }else if (box[j+1][i]==4) {
                                    box[j][i]=7;
                                    box[j+1][i]= 4;
                                }else if (box[j+1][i]==5) {
                                    box[j][i]=7;
                                    box[j+1][i]= 5;
                                }else if (box[j+1][i]==7) {
                                    box[j][i]=7;
                                    box[j+1][i]= 7;
                                }else if (box[j+1][i]==8) {
                                    box[j][i]=7;
                                    box[j+1][i]= 8;
                                }else if (box[j+1][i]==9) {
                                    box[j][i]=7;
                                    box[j+1][i]= 9;
                                }else if (box[j+1][i]==10) {
                                    box[j][i]=7;
                                    box[j+1][i]= 10;
                                }
                                playerNiaoMing.play();
                                playerNiaoMing.stop();
                            }else if(gamePeople.right){
                                gamePeople.x-=50;
                                if (box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){
                                    box[j][i]=0;
                                    box[j][i+1]=7;
                                }else if (box[j][i+1]==4) {
                                    box[j][i]=7;
                                    box[j][i+1]= 4;
                                }else if (box[j][i+1]==5) {
                                    box[j][i]=7;
                                    box[j][i+1]= 5;
                                }else if (box[j][i+1]==7) {
                                    box[j][i]=7;
                                    box[j][i+1]= 7;
                                }else if (box[j][i+1]==8) {
                                    box[j][i]=7;
                                    box[j][i+1]= 8;
                                }else if (box[j][i+1]==9) {
                                    box[j][i]=7;
                                    box[j][i+1]= 9;
                                }else if (box[j][i+1]==10) {
                                    box[j][i]=7;
                                    box[j][i+1]= 10;
                                }
                                playerNiaoMing.play();
                                playerNiaoMing.stop();
                            }else if(gamePeople.left){
                                gamePeople.x+=50;
                                if (box[j][i-1]!=3&&box[j][i-1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10){
                                    box[j][i]=0;
                                    box[j][i-1]=7;
                                }else if (box[j-1][i]==4) {
                                    box[j][i]=7;
                                    box[j][i-1]= 4;
                                }else if (box[j-1][i]==5) {
                                    box[j][i]=7;
                                    box[j][i-1]= 5;
                                }else if (box[j-1][i]==7) {
                                    box[j][i]=7;
                                    box[j][i-1]= 7;
                                }else if (box[j-1][i]==8) {
                                    box[j][i]=7;
                                    box[j][i-1]= 8;
                                }else if (box[j-1][i]==9) {
                                    box[j][i]=7;
                                    box[j][i-1]= 9;
                                }else if (box[j-1][i]==10) {
                                    box[j][i]=7;
                                    box[j][i-1]= 10;
                                }
                                playerNiaoMing.play();
                                playerNiaoMing.stop();
                            }
                        }
                        
                        break;
                        //用来画刀
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 4:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        
                        if (gamePeople.x == x && gamePeople.y == y) {
                            if (gamePeople.up){
                            	if (j>0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){
                                        box[j][i]=0;
                                        box[j-1][i]=4;
                                    }else if (box[j-1][i]==1) {
                                    	box[j][i]=0;
                                        box[j-1][i]=8;
                                        playerShu.play();
                                        playerShu.stop();
                                    }else if(box[j-1][i]==6) {
                                    	box[3][0]=6;
                                        box[j-1][i]=0;
                                        del(x-50,y,g);
                                        playerLuMing.play();
                                        playerLuMing.stop();
                                    }
                                    else {
                                        gamePeople.y+=50;
                                    }
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {

                                        box[j][i]=0;
                                        box[j+1][i]=4;
                                    }else if (box[j+1][i]==1) {
                                    	box[j][i]=0;
                                        box[j+1][i]=8;
                                        playerShu.play();
                                        playerShu.stop();
                                    }else if(box[j+1][i]==6) {
                                    	box[3][0]=6;
                                        box[j+1][i]=0;
                                        del(x-50,y,g);
                                        playerLuMing.play();
                                        playerLuMing.stop();
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}else {
                            		j--;
                            		gamePeople.y-=50;
                            	}
                                

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){
                                        box[j][i]=0;
                                        box[j][i+1]=4;
                                    }else if (box[j][i+1]==1) {
                                    	box[j][i]=0;
                                        box[j][i+1]=8;
                                        playerShu.play();
                                        playerShu.stop();
                                    }else if(box[j][i+1]==6) {
                                    	box[3][0]=6;
                                        box[j][i+1]=0;
                                        del(x-50,y,g);
                                        playerLuMing.play();
                                        playerLuMing.stop();
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x-=50;
                            	}
                                

                            }else if (gamePeople.left){
                            	if (i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i - 1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i - 1] = 4;
                                    }else if (box[j][i - 1]==1) {
                                    	box[j][i]=0;
                                        box[j][i - 1]=8;
                                        playerShu.play();
                                        playerShu.stop();
                                    }else if(box[j][i-1]==6) {
                                    	box[3][0]=6;
                                        box[j][i-1]=0;
                                        del(x-50,y,g);
                                        playerLuMing.play();
                                        playerLuMing.stop();
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i++;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(dao, x, y, null);
                        break;
                    //弓
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 5:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        if (gamePeople.x == x && gamePeople.y == y ) {
                            if (gamePeople.up){
                            	if (j-1>=0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){

                                        box[j][i]=0;
                                        box[j-1][i]=5;
                                    }else if (box[j-1][i]==9) {
                                    	box[j][i]=0;
                                        box[j-1][i]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.y+=50;
                                    }
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {

                                        box[j][i]=0;
                                        box[j+1][i]=5;
                                    }else if (box[j+1][i]==9) {
                                    	box[j][i]=0;
                                        box[j+1][i]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}else {
                            		j--;
                            		gamePeople.y-=50;
                            	}

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){

                                        box[j][i]=0;
                                        box[j][i+1]=5;
                                    }else if (box[j][i+1]==9) {
                                    	box[j][i]=0;
                                        box[j][i+1]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else  {
                            		i--;
                            		gamePeople.x-=50;
                            	}
                                

                            }else if (gamePeople.left){
                            	if (i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i+1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i - 1] = 5;
                                    }else if (box[j][i - 1]==9) {
                                    	box[j][i]=0;
                                        box[j][i - 1]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i++;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(gong, x, y, null);
                        break;
                        //鹿
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 6:
                    	x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(lu,x,y,null);
                        save(x,y,g);
                        break;
                        //羽毛
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 7:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        if (gamePeople.x == x && gamePeople.y == y) {
                            if (gamePeople.up){
                            	if (j-1>=0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){

                                        box[j][i]=0;
                                        box[j-1][i]=7;
                                    }else if (box[j-1][i]==8) {
                                    	box[j][i]=0;
                                        box[j-1][i]=9;
                                    }else {
                                        gamePeople.y+=50;
                                    }	
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {

                                        box[j][i]=0;
                                        box[j+1][i]=7;
                                    }else if (box[j+1][i]==8) {
                                    	box[j][i]=0;
                                        box[j+1][i]=9;
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}
                            	else {
                            		j--;
                            		gamePeople.y-=50;
                            	}

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){
                                        box[j][i]=0;
                                        box[j][i+1]=7;
                                    }else if (box[j][i+1]==8) {
                                    	box[j][i]=0;
                                        box[j][i+1]=9;
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x-=50;
                            	}
                                

                            }else if (gamePeople.left){
                            	if (i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i - 1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 7;
                                    }else if (box[j][i-1]==8) {
                                    	box[j][i]=0;
                                        box[j][i-1]=9;
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i++;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(yun, x, y, null);
                        break;
                        //树枝
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 8:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        del(x,y,g);
                        if (gamePeople.x == x && gamePeople.y == y) {
                            if (gamePeople.up){
                            	if (j-1>=0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){
                                        box[j][i]=0;
                                        box[j-1][i]=8;
                                    }else if (box[j-1][i]==7) {
                                    	box[j][i]=0;
                                        box[j-1][i]=9;
                                    }else {
                                        gamePeople.y+=50;
                                    }
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {
                                        box[j][i]=0;
                                        box[j+1][i]=8;
                                    }else if (box[j+1][i]==7) {
                                    	box[j][i]=0;
                                        box[j+1][i]=9;
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}else {
                            		j--;
                            		gamePeople.y-=50;
                            	}
                                

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){
                                        box[j][i]=0;
                                        box[j][i+1]=8;
                                    }else if (box[j][i+1]==7) {
                                    	box[j][i]=0;
                                        box[j][i+1]=9;
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x-=50;
                            	}

                            }else if (gamePeople.left){
                            	if (i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i - 1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 8;
                                    }else if (box[j][i-1]==7) {
                                    	box[j][i]=0;
                                        box[j][i-1]=9;
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i++;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(zhi, x, y, null);
                        break;
                        
                        //箭
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 9:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        if (gamePeople.x == x && gamePeople.y == y ) {
                            if (gamePeople.up){
                            	if (j-1>=0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){

                                        box[j][i]=0;
                                        box[j-1][i]=9;
                                    }else if (box[j-1][i]==5) {
                                    	box[j][i]=0;
                                        box[j-1][i]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.y+=50;
                                    }
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {

                                        box[j][i]=0;
                                        box[j+1][i]=9;
                                    }else if (box[j+1][i]==5) {
                                    	box[j][i]=0;
                                        box[j+1][i]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}else {
                            		j--;
                            		gamePeople.y-=50;
                            	}
                                

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){

                                        box[j][i]=0;
                                        box[j][i+1]=9;
                                    }else if (box[j][i+1]==5) {
                                    	box[j][i]=0;
                                        box[j][i+1]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x-=50;
                            	}
                                

                            }else if (gamePeople.left){
                            	if(i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i - 1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i - 1] = 9;
                                    }else if (box[j][i - 1]==5) {
                                    	box[j][i]=0;
                                        box[j][i - 1]=10;
                                        playerSheJian.play();
                                        playerSheJian.stop();
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(jian, x, y, null);
                        break;
                        //矢
                        /**
                         * 0代表空
                         * 1代表树
                         * 2代表草
                         * 3代表鸟
                         * 4代表刀
                         * 5代表弓
                         * 6代表鹿
                         * 7代表羽毛
                         * 8代表枝
                         * 9代表箭
                         * 10代表矢
                         */
                    case 10:
                    	x = (i + 1) * 50;
                        y = 50 + j * 50;
                        if (gamePeople.x == x && gamePeople.y == y) {
                            if (gamePeople.up){
                            	if(j-1>=0) {
                            		if (box[j-1][i]!=1&&box[j-1][i]!=2&&box[j-1][i]!=3&&box[j-1][i]!=4&&box[j-1][i]!=5&&box[j-1][i]!=6&&box[j-1][i]!=7&&box[j-1][i]!=8&&box[j-1][i]!=9&&box[j-1][i]!=10){
                                        box[j][i]=0;
                                        box[j-1][i]=10;
                                    }else if (box[j-1][i]==6) {

                                        playerDaoDi.play();
                                        playerDaoDi.stop();
                                    	pass = false;
                                    	
                                        break;
                                    	
                                    }else {
                                        gamePeople.y+=50;
                                    }
                            	}else {
                            		j++;
                            		gamePeople.y+=50;
                            	}
                                
                            }else if (gamePeople.down){
                            	if (j+1<11) {
                            		if (box[j+1][i]!=1&&box[j+1][i]!=2&&box[j+1][i]!=3&&box[j+1][i]!=4&&box[j+1][i]!=5&&box[j+1][i]!=6&&box[j+1][i]!=7&&box[j+1][i]!=8&&box[j+1][i]!=9&&box[j+1][i]!=10) {

                                        box[j][i]=0;
                                        box[j+1][i]=10;
                                    }else if (box[j+1][i]==6) {

                                        playerDaoDi.play();
                                        playerDaoDi.stop();
                                    	pass = false;
                                    	break;
                                    }else {
                                        gamePeople.y-=50;
                                    }
                            	}else {
                            		j--;
                            		gamePeople.y-=50;
                            	}
                                

                            }else if (gamePeople.right){
                            	if (i+1<19) {
                            		if (box[j][i+1]!=1&&box[j][i+1]!=2&&box[j][i+1]!=3&&box[j][i+1]!=4&&box[j][i+1]!=5&&box[j][i+1]!=6&&box[j][i+1]!=7&&box[j][i+1]!=8&&box[j][i+1]!=9&&box[j][i+1]!=10){
                                        box[j][i]=0;
                                        box[j][i+1]=10;
                                    }else if (box[j][i+1]==6) {

                                        playerDaoDi.play();
                                        playerDaoDi.stop();
                                    	pass = false;
                                        break;
                                    }else {
                                        gamePeople.x-=50;
                                    }
                            	}else {
                            		i--;
                            		gamePeople.x-=50;
                            	}
                                

                            }else if (gamePeople.left){
                            	if (i-1>=0) {
                            		if (box[j][i - 1]!=1&&box[j][i - 1]!=2&&box[j][i - 1]!=3&&box[j][i - 1]!=4&&box[j][i-1]!=5&&box[j][i-1]!=6&&box[j][i-1]!=7&&box[j][i-1]!=8&&box[j][i-1]!=9&&box[j][i-1]!=10) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 10;
                                    }else if (box[j][i-1]==6) {

                                        playerDaoDi.play();
                                        playerDaoDi.stop();
                                    	pass = false;
                                        break;
                                    }else {
                                        gamePeople.x+=50;
                                    }
                            	}else {
                            		i++;
                            		gamePeople.x+=50;
                            	}
                                
                            }
                        }
                        g.drawImage(shi, x, y, null);
                        break;
                    
                  
                }
                
            }
        }
        coll(gamePeople);
        colls(gamePeople);
    }
    
    

    //绘制墙体矩形
    public void save(int x,int y,Graphics g){
        if(filesize>0){
            GameBox2 gameBox = new GameBox2(mainFrame, helpFrame);
            gameBox.rect.x = x;
            gameBox.rect.y = y;
            gameBox.rect.width = 50;
            gameBox.rect.height = 50;
            rects.add(gameBox.rect);
            filesize--;
        }

        g.setColor(Color.black);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }

    public void del(int x, int y, Graphics g){
        Rectangle toRemove = null;
        for (Rectangle rect : rects) {
            if (rect.x == x && rect.y == y && rect.width == 50 && rect.height == 50) {
                toRemove = rect;
                break;
            }
        }

        if (toRemove != null) {
            rects.remove(toRemove);
            filesize--; // 仅在成功移除时更新 filesize
        }
    }

    int filesize = 0;
    public void che(){
        for(int i=0;i<box.length;i++){
            for(int j=0;j<box[0].length;j++){
                if(box[i][j]==1||box[i][j]==2){
                    filesize++;
                }
            }
        }
    }
    //判断碰撞
    public void coll(GamePeople2 gamePeople){
        for(int i=0;i<rects.size();i++){
            Rectangle rectangle = rects.get(i);
            //判断矩形是否相交
            if(gamePeople.rect.intersects(rectangle)){
                System.out.println("撞上了");
                playerCrush.play();
                playerCrush.stop();
                if(gamePeople.right){
                    gamePeople.x-=50;
                }else if(gamePeople.left){
                    gamePeople.x+=50;
                }else if(gamePeople.up){
                    gamePeople.y+=50;
                }else if(gamePeople.down){
                    gamePeople.y-=50;
                }
            }
        }
    }
    public void colls(GamePeople2 gamePeople){
    	if (gamePeople.x<=0||gamePeople.x>=1000||gamePeople.y<=0||gamePeople.y>=600)
    	{
    		if(gamePeople.right){
                gamePeople.x-=50;
            }else if(gamePeople.left){
                gamePeople.x+=50;
            }else if(gamePeople.up){
                gamePeople.y+=50;
            }else if(gamePeople.down){
                gamePeople.y-=50;
            }
    	}
    }
}
