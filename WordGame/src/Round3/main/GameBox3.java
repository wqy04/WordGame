package Round3.main;

import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.SoundMusicPlayer;
import GameMenu.util.GameUtil;

import java.awt.*;
import java.util.ArrayList;

public class GameBox3 {
    public Rectangle rect;
    public ArrayList<Rectangle> rects;
    boolean chenum = true;
    private MainFrame mainFrame;
    private HelpFrame helpFrame;
    private AudioController audioController;
    //判断本关是否胜利
    public boolean victory = true;

    //0是空
    //1是树
    //2是竹
    //4是刀
    //5是蚕
    //7是杆
    //8是丝
    //9是毛
    //10是笔
    //11是绢
    //12是帛
    //13是诗
    //14是人

    //初始地图
    int[][] box = {
            {0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0},
            {1,0,0,0,0,1,1,1,0,0,1,1,2,0,0,0,1,0},
            {1,0,2,0,0,0,2,2,2,2,0,0,0,4,1,1,1,1},
            {1,0,0,0,14,0,2,2,2,2,0,4,14,0,0,9,0,1},
            {0,1,4,0,0,0,0,0,0,0,0,5,0,0,14,5,1,1},
            {0,1,0,5,0,2,0,0,0,0,2,0,0,0,5,0,1,1},
            {0,0,1,0,0,0,0,9,1,1,0,0,0,0,0,0,1,0},
            {0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0},
    };

    //定义图片
    private Image zhu, dao, can, gan, sichou, mao, bi, juan, bo, shi, tree, ren;

    //加载图片
    private static final String ZHU = "/Round3/ui/img/zhu.png";
    private static final String DAO = "/Round3/ui/img/dao.png";
    private static final String CAN = "/Round3/ui/img/can.png";
    private static final String YANG = "/Round3/ui/img/yang.png";
    private static final String GAN = "/Round3/ui/img/gan.png";
    private static final String SICHOU = "/Round3/ui/img/sichou.png";
    private static final String MAO = "/Round3/ui/img/mao.png";
    private static final String BI = "/Round3/ui/img/bi.png";
    private static final String JUAN = "/Round3/ui/img/juan.png";
    private static final String YAN = "/Round3/ui/img/yan.png";
    private static final String SHI = "/Round3/ui/img/shi.png";
    private static final String TREE = "/Round3/ui/img/tree.png";
    private static final String REN = "/Round3/ui/img/ren.png";
    private static final String BO = "/Round3/ui/img/bo.png";

    // 定义音频资源
    private SoundMusicPlayer playerBi, playerCan, playerChengGong, playerGan, playerJuan, playerMao,
            playerMeiZhuang, playerYan, playerZhuangQiang;

    /**
     * 构造函数初始化
     */
    public GameBox3(MainFrame mainFrame, HelpFrame helpFrame){
        this.mainFrame = mainFrame;
        this.helpFrame = helpFrame;
        this.audioController = mainFrame.getAudioController();

        zhu = GameUtil.LoadBufferedImages(ZHU);
        dao = GameUtil.LoadBufferedImages(DAO);
        can = GameUtil.LoadBufferedImages(CAN);
        gan = GameUtil.LoadBufferedImages(GAN);
        sichou = GameUtil.LoadBufferedImages(SICHOU);
        mao = GameUtil.LoadBufferedImages(MAO);
        bi = GameUtil.LoadBufferedImages(BI);
        juan = GameUtil.LoadBufferedImages(JUAN);
        bo = GameUtil.LoadBufferedImages(BO);
        shi = GameUtil.LoadBufferedImages(SHI);
        tree = GameUtil.LoadBufferedImages(TREE);
        ren = GameUtil.LoadBufferedImages(REN);

        // 音频资源初始化
        playerBi = new SoundMusicPlayer("/Round3/ui/music/bi.WAV");
        playerCan = new SoundMusicPlayer("/Round3/ui/music/can.WAV");
        playerChengGong = new SoundMusicPlayer("/Round3/ui/music/chenggong.WAV");
        playerGan = new SoundMusicPlayer("/Round3/ui/music/gan.WAV");
        playerJuan = new SoundMusicPlayer("/Round3/ui/music/juan.WAV");
        playerMao = new SoundMusicPlayer("/Round3/ui/music/mao.WAV");
        playerMeiZhuang = new SoundMusicPlayer("/Round3/ui/music/meizhuang.WAV");
        playerYan = new SoundMusicPlayer("/Round3/ui/music/yan.WAV");
        playerZhuangQiang = new SoundMusicPlayer("/Round3/ui/music/zhuangqiang.WAV");
        audioController.addSound(playerBi);
        audioController.addSound(playerGan);
        audioController.addSound(playerChengGong);
        audioController.addSound(playerGan);
        audioController.addSound(playerJuan);
        audioController.addSound(playerMao);
        audioController.addSound(playerMeiZhuang);
        audioController.addSound(playerYan);
        audioController.addSound(playerZhuangQiang);

        rect = new Rectangle();
        rects = new ArrayList<>();
    }

    //界面中可以移动的汉字的位置
    int x=0,y=0;

    public void draw(Graphics g, GamePeople3 gamePeople){
        if(chenum){
            che();
            chenum=false;
        }

        victory = true;

        //循环遍历数组（找汉字去推动）
        for(int j=0;j<box.length;j++){
            for(int i=0;i<box[0].length;i++){
                switch (box[j][i]){
                    //表示空位置
                    case 0:
                        x=(i+1)*50;
                        y=50+j*50;
                        break;
                    //用来表示树，不可移动（人不出树）
                    case 1:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(tree,x,y,null);
                        save(x,y,g);
                        break;
                    //用来移动竹汉字
                    case 2:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if((box[j-1][i] != 11) && (box[j-1][i] != 4)) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 2;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 2;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("竹汉字撞到了墙上！");
                                    }else{
                                        box[j][i] = 2;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰绢,没碰刀");
                                    }
                                }else if(box[j-1][i] == 11) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 12;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与绢碰撞，形成帛");
                                }else if(box[j-1][i] == 4) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与刀碰撞，形成杆");
                                }
                            }else if(gamePeople.down) {
                                if((box[j+1][i] != 11) && (box[j+1][i] != 4)) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 2;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 2;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("竹汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 2;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰绢，没碰刀");
                                    }
                                }else if(box[j+1][i] == 11)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 12;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与绢碰撞，形成帛");
                                }else if(box[j+1][i] == 4) {
                                    box[j][i] = 0;
                                    box[j+1][i] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与刀碰撞，形成杆");
                                }
                            }else if(gamePeople.right){
                                if((box[j][i+1] != 11) && (box[j][i+1] != 4)) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 2;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 2;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("竹汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 2;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰绢,没碰刀");
                                    }
                                }else if(box[j][i+1] == 11)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 12;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与绢碰撞，形成帛");
                                }else if(box[j][i+1] == 4) {
                                    box[j][i] = 0;
                                    box[j][i+1] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与刀碰撞，形成杆");
                                }
                            }else if(gamePeople.left){
                                if((box[j][i-1] != 11) && (box[j][i-1] != 4)) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 2;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 2;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("竹汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 2;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰绢,没碰刀");
                                    }
                                }else if(box[j][i-1] == 11)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 12;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与绢碰撞，形成帛");
                                }else if(box[j][i-1] == 4) {
                                    box[j][i] = 0;
                                    box[j][i-1] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("竹与刀碰撞，形成杆");
                                }
                            }
                        }
                        g.drawImage(zhu, x, y, null);
                        break;
                    //用来移动刀汉字
                    case 4:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 2) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 4;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 4;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("刀汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 4;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j-1][i] == 2) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("刀与竹碰撞，形成杆");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 2) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 4;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 4;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("刀汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 4;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j+1][i] == 2)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("刀和竹碰撞，形成杆");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 2) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 4;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 4;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("刀汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 4;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j][i+1] == 2)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("刀和竹碰撞，形成杆");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 2) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 4;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 4;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("刀汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 4;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j][i-1] == 2)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 7;
                                    playerGan.play();
                                    playerGan.stop();
                                    System.out.println("刀和竹碰撞，形成杆");
                                }
                            }
                        }
                        g.drawImage(dao,x,y,null);
                        break;
                    //用来移动蚕汉字
                    case 5:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 14) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 5;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 5;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("蚕汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 5;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰人");
                                    }
                                }else if(box[j-1][i] == 14) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("蚕与人碰撞，形成丝");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 14) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 5;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 5;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("蚕汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 5;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰人");
                                    }
                                }else if(box[j+1][i] == 14)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("蚕与人碰撞，形成丝");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 14) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 5;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 5;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("蚕汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 5;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰人");
                                    }
                                }else if(box[j][i+1] == 14)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("蚕与人碰撞，形成丝");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 14) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 5;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 5;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("蚕汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 5;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰人");
                                    }
                                }else if(box[j][i-1] == 14)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("蚕与人碰撞，形成丝");
                                }
                            }
                        }
                        g.drawImage(can,x,y,null);
                        break;
                    //用来移动杆汉字
                    case 7:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 9) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 7;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 7;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("杆汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 7;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰毛");
                                    }
                                }else if(box[j-1][i] == 9) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("杆与毛碰撞，形成笔");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 9) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 7;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 7;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("杆汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 7;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰毛");
                                    }
                                }else if(box[j+1][i] == 9)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("杆与毛碰撞，形成笔");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 9) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 7;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 7;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("杆汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 7;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰毛");
                                    }
                                }else if(box[j][i+1] == 9)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("杆与毛碰撞，形成笔");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 9) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 7;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 7;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("杆汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 7;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰毛");
                                    }
                                }else if(box[j][i-1] == 9)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("杆与毛碰撞，形成笔");
                                }
                            }
                        }
                        g.drawImage(gan,x,y,null);
                        break;
                    //用来移动丝汉字
                    case 8:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 8) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 8;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 8;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("丝汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 8;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰丝");
                                    }
                                }else if(box[j-1][i] == 8) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 11;
                                    playerJuan.play();
                                    playerJuan.stop();
                                    System.out.println("丝与丝碰撞，形成绢");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 8) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 8;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 8;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("丝汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 8;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰丝");
                                    }
                                }else if(box[j+1][i] == 8)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 11;
                                    playerJuan.play();
                                    playerJuan.stop();
                                    System.out.println("丝与丝碰撞，形成绢");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 8) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 8;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 8;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("丝汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 8;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰丝");
                                    }
                                }else if(box[j][i+1] == 8)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 11;
                                    playerJuan.play();
                                    playerJuan.stop();
                                    System.out.println("丝与丝碰撞，形成绢");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 8) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 8;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 8;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("丝汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 8;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰丝");
                                    }
                                }else if(box[j][i-1] == 8)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 11;
                                    playerJuan.play();
                                    playerJuan.stop();
                                    System.out.println("丝与丝碰撞，形成绢");
                                }
                            }
                        }
                        g.drawImage(sichou,x,y,null);
                        break;
                    //用来移动毛汉字
                    case 9:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 7) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 9;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 9;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("毛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 9;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰杆");
                                    }
                                }else if(box[j-1][i] == 7) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("毛与杆碰撞，形成笔");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 7) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 9;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 9;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("毛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 9;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰杆");
                                    }
                                }else if(box[j+1][i] == 7)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("毛与杆碰撞，形成笔");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 7) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 9;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 9;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("毛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 9;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰杆");
                                    }
                                }else if(box[j][i+1] == 7)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("毛与杆碰撞，形成笔");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 7) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 9;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 9;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("毛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 9;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰杆");
                                    }
                                }else if(box[j][i-1] == 7)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 10;
                                    playerBi.play();
                                    playerBi.stop();
                                    System.out.println("毛与杆碰撞，形成笔");
                                }
                            }
                        }
                        g.drawImage(mao,x,y,null);
                        break;
                    //用来移动笔汉字
                    case 10:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 12) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 10;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 10;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("笔汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 10;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰帛");
                                    }
                                }else if(box[j-1][i] == 12) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 13;
                                    g.drawImage(shi,gamePeople.x,gamePeople.y-50,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("笔与帛碰撞，形成诗");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 12) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 10;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 10;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("笔汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 10;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰帛");
                                    }
                                }else if(box[j+1][i] == 12)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 13;
                                    g.drawImage(shi,gamePeople.x,gamePeople.y+50,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("笔与帛碰撞，形成诗");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 12) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 10;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 10;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("笔汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 10;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰帛");
                                    }
                                }else if(box[j][i+1] == 12)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 13;
                                    g.drawImage(shi,gamePeople.x+50,gamePeople.y,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("笔与帛碰撞，形成诗");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 12) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 10;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 10;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("笔汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 10;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰帛");
                                    }
                                }else if(box[j][i-1] == 12)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 13;
                                    g.drawImage(shi,gamePeople.x-50,gamePeople.y,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("笔与帛碰撞，形成诗");
                                }
                            }
                        }
                        g.drawImage(bi,x,y,null);
                        break;
                    //用来移动绢
                    case 11:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 2) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 11;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 11;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("绢汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 11;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j-1][i] == 2) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 12;
                                    playerYan.play();
                                    playerYan.stop();
                                    System.out.println("绢与竹碰撞，形成帛");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 2) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 11;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 11;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("绢汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 11;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j+1][i] == 2)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 12;
                                    playerYan.play();
                                    playerYan.stop();
                                    System.out.println("绢与竹碰撞，形成帛");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 2) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 11;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 11;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("绢汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 11;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j][i+1] == 2)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 12;
                                    playerYan.play();
                                    playerYan.stop();
                                    System.out.println("绢与竹碰撞，形成帛");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 2) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 11;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 11;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("绢汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 11;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰竹");
                                    }
                                }else if(box[j][i-1] == 2)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 12;
                                    playerYan.play();
                                    playerYan.stop();
                                    System.out.println("绢与竹碰撞，形成帛");
                                }
                            }
                        }
                        g.drawImage(juan,x,y,null);
                        break;
                    //用来移动帛汉字
                    case 12:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if(box[j-1][i] != 10) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 12;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 12;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("帛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 12;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰笔");
                                    }
                                }else if(box[j-1][i] == 10) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 13;
                                    g.drawImage(shi,gamePeople.x,gamePeople.y-50,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("帛与笔碰撞，形成诗");
                                }
                            }else if(gamePeople.down) {
                                if(box[j+1][i] != 10) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 12;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 12;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("帛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 12;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰笔");
                                    }
                                }else if(box[j+1][i] == 10)  {
                                    box[j][i] = 0;
                                    box[j+1][i] = 13;
                                    g.drawImage(shi,gamePeople.x,gamePeople.y+50,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("帛与笔碰撞，形成诗");
                                }
                            }else if(gamePeople.right){
                                if(box[j][i+1] != 10) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 12;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 12;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("帛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 12;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰笔");
                                    }
                                }else if(box[j][i+1] == 10)  {
                                    box[j][i] = 0;
                                    box[j][i+1] = 13;
                                    g.drawImage(shi,gamePeople.x+50,gamePeople.y,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("帛与笔碰撞，形成诗");
                                }
                            }else if(gamePeople.left){
                                if(box[j][i-1] != 10) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 12;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 12;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("帛汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 12;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰笔");
                                    }
                                }else if(box[j][i-1] == 10)  {
                                    box[j][i] = 0;
                                    box[j][i-1] = 13;
                                    g.drawImage(shi,gamePeople.x-50,gamePeople.y,null);
                                    playerChengGong.play();
                                    playerChengGong.stop();
                                    System.out.println("帛与笔碰撞，形成诗");
                                }
                            }
                        }
                        g.drawImage(bo,x,y,null);
                        break;
                    //用来移动诗汉字
                    case 13:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                box[j][i]=0;
                                box[j-1][i]=13;
                            }else if(gamePeople.down) {
                                box[j][i] = 0;
                                box[j+1][i] = 13;
                            }else if(gamePeople.right){
                                box[j][i] = 0;
                                box[j][i+1]=13;
                            }else if(gamePeople.left){
                                box[j][i] = 0;
                                box[j][i-1] = 13;
                            }
                        }
                        victory = false;
                        break;
                    //用来移动人
                    case 14:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if((box[j-1][i] != 5) && (box[j-1][i] != 6)) {
                                    if(box[j-1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j-1][i] = 14;
                                    }else if(box[j-1][i] == 1) {
                                        box[j][i] = 14;
                                        box[j-1][i] = 1;
                                        gamePeople.y = y+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("人汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 14;
                                        gamePeople.y = y+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰蚕，羊");
                                    }
                                }else if(box[j-1][i] == 5) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("人与蚕碰撞，形成丝");
                                }else if(box[j-1][i] == 6) {
                                    box[j][i] = 0;
                                    box[j-1][i] = 9;
                                    System.out.println("人与羊碰撞，形成毛");
                                }
                            }else if(gamePeople.down) {
                                if((box[j+1][i] != 5) && (box[j+1][i] != 6)) {
                                    if(box[j+1][i] == 0) {
                                        box[j][i] = 0;
                                        box[j+1][i] = 14;
                                    }else if(box[j+1][i] == 1) {
                                        box[j][i] = 14;
                                        box[j+1][i] = 1;
                                        gamePeople.y = y-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("人汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 14;
                                        gamePeople.y = y-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰蚕，羊");
                                    }
                                }else if(box[j+1][i] == 5) {
                                    box[j][i] = 0;
                                    box[j+1][i] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("人与蚕碰撞，形成丝");
                                }else if(box[j+1][i] == 6) {
                                    box[j][i] = 0;
                                    box[j+1][i] = 9;
                                    playerMao.play();
                                    playerMao.stop();
                                    System.out.println("人与羊碰撞，形成毛");
                                }
                            }else if(gamePeople.right){
                                if((box[j][i+1] != 5) && (box[j][i+1] != 6)) {
                                    if(box[j][i+1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i+1] = 14;
                                    }else if(box[j][i+1] == 1) {
                                        box[j][i] = 14;
                                        box[j][i+1] = 1;
                                        gamePeople.x = x-50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("人汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 14;
                                        gamePeople.x = x-50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰蚕，羊");
                                    }
                                }else if(box[j][i+1] == 5) {
                                    box[j][i] = 0;
                                    box[j][i+1] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("人与蚕碰撞，形成丝");
                                }else if(box[j][i+1] == 6) {
                                    box[j][i] = 0;
                                    box[j][i+1] = 9;
                                    playerMao.play();
                                    playerMao.stop();
                                    System.out.println("人与羊碰撞，形成毛");
                                }
                            }else if(gamePeople.left){
                                if((box[j][i-1] != 5) && (box[j][i-1] != 6)) {
                                    if(box[j][i-1] == 0) {
                                        box[j][i] = 0;
                                        box[j][i-1] = 14;
                                    }else if(box[j][i-1] == 1) {
                                        box[j][i] = 14;
                                        box[j][i-1] = 1;
                                        gamePeople.x = x+50;
                                        playerZhuangQiang.play();
                                        playerZhuangQiang.stop();
                                        System.out.println("人汉字撞到了墙上！");
                                    }else {
                                        box[j][i] = 14;
                                        gamePeople.x = x+50;
                                        playerMeiZhuang.play();
                                        playerMeiZhuang.stop();
                                        System.out.println("没碰蚕，羊");
                                    }
                                }else if(box[j][i-1] == 5) {
                                    box[j][i] = 0;
                                    box[j][i-1] = 8;
                                    playerCan.play();
                                    playerCan.stop();
                                    System.out.println("人与蚕碰撞，形成丝");
                                }else if(box[j][i-1] == 6) {
                                    box[j][i] = 0;
                                    box[j][i-1] = 9;
                                    playerMao.play();
                                    playerMao.stop();
                                    System.out.println("人与羊碰撞，形成毛");
                                }
                            }
                        }
                        g.drawImage(ren,x,y,null);
                        break;
                }
            }
        }
        coll(gamePeople);
    }


    //绘制汉字的矩形
    public void save(int x,int y,Graphics g){
        if(filesize>0){
            GameBox3 gameBox = new GameBox3(mainFrame, helpFrame);
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
    //记录有多少个箱子
    public void che(){
        for(int i=0;i<box.length;i++){
            for(int j=0;j<box[0].length;j++){
                if(box[i][j]==1){//代表这个位置是箱子
                    filesize++;
                }
            }
        }
    }

    //判断人与矩形的碰撞
    public void coll(GamePeople3 gamePeople){
        for(int i=0;i<rects.size();i++){
            Rectangle rectangle = rects.get(i);
            //判断矩形是否相交
            if(gamePeople.rect.intersects(rectangle)){
                System.out.println("撞上了");
                if(gamePeople.up){
                    gamePeople.y+=50;
                }else if(gamePeople.down){
                    gamePeople.y-=50;
                }else if(gamePeople.left){
                    gamePeople.x+=50;
                }else if(gamePeople.right) {
                    gamePeople.x-=50;
                }
            }
        }
    }
}
